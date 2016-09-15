#!/usr/bin/env Rscript

args = commandArgs(trailingOnly=TRUE)

if (length(args)==0) {
  stop("At least one argument must be supplied", call.=FALSE)
  
}

if(args=='-h')
{
  stop("Please input the path to the desired run folder")
}

main <- function(arg1){
  try(setwd(arg1))
  
  cat("Importing run parameters\n")
  run_info <- run_parameters()
  
  cat("\nGenerating Interop output.csv file\n")
  path <- paste("/Users/nazamy/Desktop/interop/osx_clang_release/bin/interop2csv ", arg1, " > output.csv")
  system(path, intern = T)
  cat("Generation complete\n")
  
  cat("Building parsing index\n")
  headers <- system(("grep -nv \'^[1-9]\' output.csv"), intern = T)
  num_col <- max(count.fields("output.csv", sep = ",", blank.lines.skip = F))
  raw_data <- read.csv("output.csv", header = F, col.names = rep("X",num_col))
  cat("Parse index complete\n")
  
  cat("Generating Q Score plot\n")
  qscore(headers, run_info)
  cat("Q Score plot generated\n")
  cat("Generating Base Distribution Plot\n")
  base_distribution(headers, run_info)
  cat("Base Distribution Plot generated\n")
  cat("Generating Base Intensity plot\n")
  intensities(headers, run_info)
  cat("Base Intensity plot generated\n")
  cat("This program will now self terminate\n")
  file.remove("output.csv")
}

#generating run parameters
run_parameters <- function(){
  machine_type <- system(paste("sed -n \'/ApplicationName/{s/.*<ApplicationName>//;s/<\\/ApplicationName.*//;p;}\' [Rr]unParameters.xml"), intern = T)
  machine_type <- unlist(strsplit(machine_type," "))[1]
  
  if(machine_type =="NextSeq")
  {
    read_1_len <- system(paste("sed -n \'/<Read1/{s/.*<Read1>//;s/<\\/Read1>\r$//;p;}\' [Rr]unParameters.xml"), intern = T)
    read_2_len <- system(paste("sed -n \'/<Read2/{s/.*<Read2>//;s/<\\/Read2>\r$//;p;}\' [Rr]unParameters.xml"), intern = T)
    index_len <- system(paste("sed -n \'/Index1Read/{s/.*<Index1Read>//;s/<\\/Index1Read>\r$//;p;}\' [Rr]unParameters.xml"), intern = T)
    index_len <- index_len[1]
  }
  if(machine_type == "HiSeq"){
    read_1_len <- system(paste("sed -n \'/<Read1/{s/.*<Read1>//;s/<\\/Read1>//;p;}\' [Rr]unParameters.xml"), intern = T)
    read_2_len <- system(paste("sed -n \'/<Read2/{s/.*<Read2>//;s/<\\/Read2>//;p;}\' [Rr]unParameters.xml"), intern = T)
    index_len <- system(paste("sed -n \'/IndexRead1/{s/.*<IndexRead1>//;s/<\\/IndexRead1>//;p;}\' [Rr]unParameters.xml"), intern = T)
  }
  (cat("Run:", machine_type, read_1_len, "x", index_len,"x",read_2_len))
  return(array(c(machine_type,read_1_len,read_2_len,index_len)))
}

#qscore generation
qscore <- function(headers, run_info){
  bin_header<- grep("BinCount", headers)
  startline_bins <- as.numeric(regmatches(headers[bin_header], regexpr("^[0-9]*", headers[bin_header]))) + 1
  endline_bins <- as.numeric(regmatches(headers[bin_header + 1], regexpr("^[0-9]*", headers[bin_header]))) - 1
  #gen table and column names
  bin_table <- read.table("output.csv", sep = ",", header = T, skip = (startline_bins-4), nrows = (endline_bins - startline_bins+1))

  if(run_info[1] == "HiSeq")
  {
  pct_aboveQ30 <- apply(bin_table[-c(1:33)],1,sum)/apply(bin_table[-c(1:4)],1,sum)
  }
  if(run_info[1] == "NextSeq")
  {
  pct_aboveQ30 <- apply(bin_table[-c(1:8)],1,sum)/apply(bin_table[-c(1:4)],1,sum)
  }
  pct_aboveQ30 <- data.frame(cbind(Cycle=bin_table$Cycle,pct_aboveQ30))
  levels(pct_aboveQ30$Cycle) <- unique(pct_aboveQ30$Cycle)
  Q30_by_cycle <- by(pct_aboveQ30$pct_aboveQ30, pct_aboveQ30$Cycle, median,na.rm=T)
  run_length <- as.numeric(run_info[2:4])
  Q30_read_1_median <- median(Q30_by_cycle[1:run_length[1]])
  Q30_read_2_median <- median((Q30_by_cycle[(run_length[1]+run_length[3]+1):sum(run_length)]))
  
  cat("\n**Read 1 Q30 Stats**\n")
  
  cat(paste("Median Q30 Value: ", round(Q30_read_1_median,digits = 3)))
  cat(paste("\n25% Quantile: ", round(quantile(Q30_by_cycle[(1:run_length[1])],probs = 0.25),digits = 3), "\n"))
  
  cat("\n**Read 2 Q30 Stats**\n")
  
  cat(paste("Median Q30 Value: ", round(Q30_read_2_median,digits = 3)))
  cat(paste("\n25% Quantile: ", round(quantile(Q30_by_cycle[(run_length[1]+run_length[3]+1):sum(run_length)], probs = 0.25),digits = 3),"\n\n"))
  
  png("Percent_q30.png")
  plot(Q30_by_cycle, xlab = "Cycles", ylab = "% Above Q30", ylim = c(0,1),main ="Median % Bases Above Q30 by Cycle", type = "l")
  #dev.copy(png,'Percent_q30.png')
  dev.off()
}

#base diversity plot generation
base_distribution <- function(headers, run_info){
int_header<- grep("AverageCycleIntensity", headers)
startline_int <- as.numeric(regmatches(headers[int_header], regexpr("^[0-9]*", headers[int_header])))+1
if(run_info[1] == "NextSeq"){
endline_int <- as.numeric(regmatches(headers[int_header + 1], regexpr("^[0-9]*", headers[int_header+1])))-1
}
if(run_info[1] == "HiSeq"){
endline_int <- as.numeric(regmatches(headers[int_header + 1], regexpr("^[0-9]*", headers[int_header])))-1
}
  #generate table and column names
int_table <- read.table("output.csv", sep = ",", header = T, skip = (startline_int-4), nrows = (endline_int - startline_int+1))

  #calculate percent bases per tile
all_bases <- grep("CallCount_", colnames(int_table))
base_a <-(int_table["CallCount_A"])/apply((int_table[all_bases]),1,sum)
base_t <-(int_table["CallCount_T"])/apply((int_table[all_bases]),1,sum)
base_g <-(int_table["CallCount_G"])/apply((int_table[all_bases]),1,sum)
base_c <-(int_table["CallCount_C"])/apply((int_table[all_bases]),1,sum)
  #collapse tiles
pct_bases <- data.frame(cbind(Cycle=int_table$Cycle,base_a,base_t,base_g,base_c))
levels(pct_bases$Cycle) <- unique(pct_bases$Cycle)

pct_base.a<-by(pct_bases$CallCount_A,pct_bases$Cycle,median,na.rm=T)
pct_base.t<-by(pct_bases$CallCount_T,pct_bases$Cycle,median,na.rm=T)
pct_base.g<-by(pct_bases$CallCount_G,pct_bases$Cycle,median,na.rm=T)
pct_base.c<-by(pct_bases$CallCount_C,pct_bases$Cycle,median,na.rm=T)
  #plot median % bases per tile

run_length <- as.numeric(run_info[2:4])

pctA_read_1_median <- round(median(pct_base.a[1:run_length[1]]),digits = 3)
pctT_read_1_median <- round(median(pct_base.t[1:run_length[1]]),digits = 3)
pctG_read_1_median <- round(median(pct_base.g[1:run_length[1]]), digits = 3)
pctC_read_1_median <- round(median(pct_base.c[1:run_length[1]]),digits = 3)

pctA_quartile_1 <- round(quantile(pct_base.a[(1:run_length[1])],probs = 0.25),digits = 3)
pctT_quartile_1 <- round(quantile(pct_base.t[(1:run_length[1])],probs = 0.25),digits = 3)
pctG_quartile_1 <- round(quantile(pct_base.g[(1:run_length[1])],probs = 0.25),digits = 3)
pctC_quartile_1 <- round(quantile(pct_base.c[(1:run_length[1])],probs = 0.25),digits = 3)

pctA_read_2_median <- round(median((pct_base.a[(run_length[1]+run_length[3]+1):sum(run_length)])),digits = 3)
pctT_read_2_median <- round(median((pct_base.t[(run_length[1]+run_length[3]+1):sum(run_length)])), digits = 3)
pctG_read_2_median <- round(median((pct_base.g[(run_length[1]+run_length[3]+1):sum(run_length)])), digits = 3)
pctC_read_2_median <- round(median((pct_base.c[(run_length[1]+run_length[3]+1):sum(run_length)])), digits = 3)

pctA_quartile_2 <- round(quantile((pct_base.a[(run_length[1]+run_length[3]+1):sum(run_length)]),probs = 0.25),digits = 3)
pctT_quartile_2 <- round(quantile((pct_base.t[(run_length[1]+run_length[3]+1):sum(run_length)]),probs = 0.25),digits = 3)
pctG_quartile_2 <- round(quantile((pct_base.g[(run_length[1]+run_length[3]+1):sum(run_length)]),probs = 0.25),digits = 3)
pctC_quartile_2 <- round(quantile((pct_base.c[(run_length[1]+run_length[3]+1):sum(run_length)]),probs = 0.25),digits = 3)

cat("\n**Read 1 Base % Stats**\n")

cat(paste("\nMedian A % Base: ", pctA_read_1_median))
cat(paste("\n25% Quantile: ",pctA_quartile_1))
cat(paste("\nMedian T % Base: ", pctT_read_1_median))
cat(paste("\n25% Quantile: ",pctT_quartile_1))
cat(paste("\nMedian G % Base: ", pctG_read_1_median))
cat(paste("\n25% Quantile: ",pctG_quartile_1))
cat(paste("\nMedian C % Base: ", pctC_read_1_median))
cat(paste("\n25% Quantile: ",pctC_quartile_1, "\n"))


cat("\n**Read 2 Base % Stats**\n")

cat(paste("\nMedian A % Base: ", pctA_read_2_median))
cat(paste("\n25% Quantile: ", pctA_quartile_2))
cat(paste("\nMedian T % Base: ", pctT_read_2_median))
cat(paste("\n25% Quantile: ", pctT_quartile_2))
cat(paste("\nMedian G % Base: ", pctG_read_2_median))
cat(paste("\n25% Quantile: ", pctG_quartile_2))
cat(paste("\nMedian C % Base: ", pctC_read_2_median))
cat(paste("\n25% Quantile: ", pctC_quartile_2, "\n\n"))



png("Base_Distribution.png")
plot(pct_base.a, type = "l", col = "red", xlab = "Cycle", ylab = "% Base", ylim =c(0,1), main = "Median Base Distribution per Cycle")
lines(pct_base.g, type = "l", col = "green")
lines(pct_base.t, type = "l", col = "blue")
lines(pct_base.c, type = "l", col = "black")
legend("topright", c("A", "G","T","C"), col = c("red","green", "blue", "black"), pch = 19)
#dev.copy(png,'Base_Distribution.png')
dev.off()
}

#intensities
intensities <- function(headers, run_info){
  int_header<- grep("AverageCycleIntensity", headers)
  startline_int <- as.numeric(regmatches(headers[int_header], regexpr("^[0-9]*", headers[int_header])))+1
  if(run_info[1] == "HiSeq"){
  endline_int <- as.numeric(regmatches(headers[int_header + 1], regexpr("^[0-9]*", headers[int_header])))-1
  }
  if(run_info[1] == "NextSeq"){
    endline_int <- as.numeric(regmatches(headers[int_header + 1], regexpr("^[0-9]*", headers[int_header+1])))-1
  }
  int_table <- read.table("output.csv", sep = ",", header = T, skip = (startline_int-4), nrows = ((endline_int - startline_int)+1))
  all_int <- grep("CalledIntensity_", colnames(int_table))
  int_bases <- data.frame(cbind(Cycle= int_table$Cycle,CalledIntensity_A=int_table$CalledIntensity_A,CalledIntensity_T=int_table$CallCount_T,CalledIntensity_G=int_table$CalledIntensity_G, CalledIntensity_C=int_table$CalledIntensity_C))
  levels(int_bases$Cycle) <- unique(int_bases$Cycle)
    #collapse tiles
  int_a <- by(int_bases$CalledIntensity_A,int_bases$Cycle,median, na.rm=T)
  int_t <- by(int_bases$CalledIntensity_T,int_bases$Cycle,median, na.rm=T)
  int_g <- by(int_bases$CalledIntensity_G,int_bases$Cycle,median, na.rm=T)
  int_c <- by(int_bases$CalledIntensity_C,int_bases$Cycle,median, na.rm=T)
    #plot median intensities per cycle
  
  png("Base_Intensities.png")
  plot(int_a, type = "l", col = "red", xlab = "Cycle", ylab = "Intensity", main = "Median Intensity per Cycle")
  lines(int_t, type = "l", col = "green")
  lines(int_g, type = "l", col = "blue")
  lines(int_c, type = "l", col = "black")
  legend("bottomright", c("A", "G","T","C"), col = c("red","green", "blue", "black"), pch = 19)
  
  #dev.copy(png,'Base_Intensities.png')
  dev.off()
}

main(args)

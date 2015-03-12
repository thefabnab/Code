corr <- function(directory, threshold = 0) 
{
  setwd("/Users/thefabnab/Desktop/Work/Google Drive/School Work/Intro to R Programming/Week Two/Homework/")
  ##set working director
  
  #Assigning directory
  if(grep("specdata", directory) == 1) 
  {
    directory <- ("./specdata/")
  }
  
  all_complete_data <- complete("specdata", 1:332)
  nobs <- all_complete_data$nobs
  above_threshold <- all_complete_data$id[nobs > threshold]
  
  no_ids <- length(above_threshold)
  correlation <- rep(0, no_ids)
  
  filelist <- list.files(directory)
  charFileList <- as.character(filelist)
  paths <- paste(directory, charFileList, sep="")
  
  count <- 1
  
  for(i in above_threshold)
  {
    specFile <- read.csv(paths[i], header=T, sep=",")
    correlation[count] <- cor(specFile$sulfate, specFile$nitrate, use="complete.obs")
    count <- count + 1
  }

  answer <- correlation
  return(answer)   
}
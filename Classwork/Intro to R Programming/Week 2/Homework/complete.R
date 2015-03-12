
complete <- function(directory, id = 1:332)
{
  setwd("/Users/thefabnab/Desktop/Work/Google Drive/School Work/Intro to R Programming/Week Two/Homework/")
  ##Set working directory to folder path
  
  #Assigning directory
  if(grep("specdata", directory) == 1) 
  {
    directory <- ("./specdata/")
  }
  
  filelist <- list.files(directory)
  charFileList <- as.character(filelist)
  paths <- paste(directory, charFileList, sep="")
  
  total_length <- length(id)
  data <- rep(0, total_length)
  count <- 1
  
  for (i in id)
  {
    specFile <- read.csv(paths[i], header=T, sep=",")
    data[count] <- sum(complete.cases(specFile))
    count <- count + 1
  }
  
  answer <- data.frame(id = id, nobs = data)
  return(answer)
}

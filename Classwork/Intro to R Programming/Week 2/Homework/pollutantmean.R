pollutantmean <- function(directory, pollutant, id = 1:332)
{ 
  setwd("/Users/thefabnab/Desktop/Work/Google Drive/School Work/Intro to R Programming/Week Two/Homework/")
  ##Set working directory to folder path
  
  #Assigning directory
  if(grep("specdata", directory) == 1) 
    {
    directory <- ("./specdata/")
    }
  #
  mean_vector <- c()
  charFileList <- as.character(list.files(directory))
  paths <- paste(directory, charFileList, sep="")
  for(i in id)
  {
    specFile <- read.csv(paths[i], header=T, sep=",")
    head(specFile)
    pollutant
    removeNA <- specFile[!is.na(specFile[, pollutant]), pollutant]
    mean_vector <- c(mean_vector, removeNA)
  }
  answer <- mean(mean_vector)
  return(round(answer, 3))  
}
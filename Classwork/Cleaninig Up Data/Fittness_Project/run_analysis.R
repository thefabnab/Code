#Establishing working directory to access data and required libraries
setwd("Work/Google Drive/School Work/Cleaning Up Data/Homework/UCI HAR Dataset/")
library(data.table)

#combining training and test datasets
x_train <- read.table("train/X_train.txt")
x_test <- read.table("test/X_test.txt")
x <- rbind(x_train, x_test)

sub_train <- read.table("train/subject_train.txt")
sub_test <- read.table("test/subject_test.txt")
sub <- rbind(sub_train, sub_test)

y_train <- read.table("train/y_train.txt")
y_test <- read.table("test/y_test.txt")
y <- rbind(y_train, y_test)

#parses only the mean and std from features and combines with x
features <- read.table("features.txt")
mean_std <- grep("-mean\\(\\)|-std\\(\\)", features[, 2])
x_mean_std <- x[, mean_std]

#Provied activities with specific names
names(x_mean_std) <- features[mean_std, 2]
names(x_mean_std) <- tolower(names(x_mean_std)) 
names(x_mean_std) <- gsub("\\(|\\)", "", names(x_mean_std))

activity <- read.table("activity_labels.txt")
activity[, 2] <- tolower(as.character(activity[, 2]))
activity[, 2] <- gsub("_", "", activity[, 2])

y[, 1] = activity[y[, 1], 2]
y_colnames(y) <- "activity"
y_colnames(sub) <- "subject"

#
fin_data <- cbind(sub, x_mean_std, y)
names(fin_data)[1]<-paste("subject")
names(fin_data)[68]<-paste("activity")
str(fin_data)
write.table(fin_data, "Merged_Data.txt")

#
tidy_data_average <- aggregate(x=fin_data, 
                               by=list(activities=fin_data$activity, 
                                       subjects=fin_data$subject), FUN=mean)
str(tidy_data_average)
write.table(tidy_data_average, "tidy_data_average.txt")

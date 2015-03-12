library(data.table)
library(plyr)
#Obtaining data and modifying dates
data <- read.csv2("household_power_consumption.txt")
data <- data.table(data)
data$Date <- as.Date(data$Date, format="%d/%m/%Y")

setwd("Desktop/Work/Google Drive//School Work//Exploratory Data Analysis//Project 1")
#Subsetting dates 02-01-07 and 02-02-07 from data
sub_data <- subset(data, subset=(Date >= "2007-02-01" & Date <= "2007-02-02"))

#Combining Time and Date columns into date column
TimeDate <- as.POSIXct(paste(sub_data$Date, sub_data$Time), 
                       format="%Y-%m-%d %H:%M:%S")
sub_data$Date <- TimeDate
sub_data <- mutate(sub_data, Global_active_power = 
                   as.numeric(Global_active_power)/1000)

#Plot 2
plot(sub_data$Date, sub_data$Global_active_power, type = "l", xlab = "Date",
     ylab = "Global Active Power")


#Save copy of the plot
dev.copy(png, file="plot2.png", height=480, width=480)
dev.off()
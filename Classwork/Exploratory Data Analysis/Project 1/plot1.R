setwd("Desktop/Work/Google Drive//School Work//Exploratory Data Analysis//Project 1")
library(data.table)
#Obtaining data and modifying dates
data <- read.csv2("household_power_consumption.txt")
data <- data.table(data)
data$Date <- as.Date(data$Date, format="%d/%m/%Y")

#Subsetting dates 02-01-07 and 02-02-07 from data
sub_data <- subset(data, subset=(Date >= "2007-02-01" & Date <= "2007-02-02"))

#Histogram for plot 1
hist(as.numeric(sub_data$Global_active_power)/1000, main = "Global Active Power"
     , xlab = "Global Active Power (kilowatts)", ylab = "Frequency", 
     col = "RED")

#Save copy of the plot
dev.copy(png, file="plot1.png", height=480, width=480)
dev.off()
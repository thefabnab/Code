setwd("Desktop/Work/Google Drive//School Work//Exploratory Data Analysis//Project 1")
library(data.table)
library(plyr)
#Obtaining data and modifying dates
data <- read.csv2("household_power_consumption.txt")
data <- data.table(data)
data$Date <- as.Date(data$Date, format="%d/%m/%Y")

#Subsetting dates 02-01-07 and 02-02-07 from data
sub_data <- subset(data, subset=(Date >= "2007-02-01" & Date <= "2007-02-02"))

#Combining Time and Date columns into Date column
TimeDate <- as.POSIXct(paste(sub_data$Date, sub_data$Time), 
                       format="%Y-%m-%d %H:%M:%S")
sub_data$Date <- TimeDate
sub_data <- mutate(sub_data, Global_active_power = 
                       as.numeric(Global_active_power)/1000)

#Producing Plot 4
par(mfrow = c(2, 2), mar = c(2,4,2,2))

with(sub_data, {
    plot(Date, Global_active_power, type = "l", ylab = "Global Active Power")
    plot(Date, Voltage, type = "l", ylab = "Voltage")
    plot(Date, Sub_metering_1, type="l",
         ylab="Global Active Power (kilowatts)", xlab="")
    lines(Date, Sub_metering_2,col='Red')
    lines(Date, Sub_metering_3,col='Blue')
    legend("topright", col=c("black", "red", "blue"), lty=1, lwd=2, bty="n",
           legend=c("Sub_metering_1", "Sub_metering_2", "Sub_metering_3"))
    plot(Date, Global_reactive_power, type = "l", ylab ="Global Reactive Power")
})

# Saving copy of the plot
dev.copy(png, file="plot3.png", height=480, width=480)
dev.off()
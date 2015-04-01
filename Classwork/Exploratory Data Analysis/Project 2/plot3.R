#Establishing WD
library(data.table)
library(ggplot2)
setwd("Desktop/Work/Google_Drive/School Work/Exploratory Data Analysis/Project 2/exdata-data-NEI_data/")

#reading provided project data
NEI <- readRDS("summarySCC_PM25.rds")
SCC <- readRDS("Source_Classification_Code.rds")

#Question number three:
ggplot(baltimore,aes(factor(year),Emissions,fill=type)) + 
    geom_bar(stat="identity") + 
    facet_grid(.~type,scales = "free",space="free") +
    labs(title = "PM2.5 Emissions in Baltimore by type", x = "Year",
         y = "Total Emissions")

# Save copy of the plot 3
dev.copy(png, file="plot3.png", height=480, width=480)
dev.off()
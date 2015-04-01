#Establishing WD and libraries
library(data.table)
library(ggplot2)
setwd("Desktop/Work/Google_Drive/School Work/Exploratory Data Analysis/Project 2/exdata-data-NEI_data/")

#reading provided project data
NEI <- readRDS("summarySCC_PM25.rds")
SCC <- readRDS("Source_Classification_Code.rds")

#Subsetting from SCC to find coal sources
vehicle <- grepl("vehicle", SCC$SCC.Level.Two, ignore.case=TRUE)
SCC_vehicle <- SCC[vehicle,]$SCC
NEI_vehicles <- NEI[NEI$SCC %in% SCC_vehicle,]
baltimore_vehicles <- subset(NEI_vehicles, fips == "24510")
View(head(baltimore_vehicles))

#plotting baltimore_vehicles
ggplot(baltimore_vehicles,aes(factor(year),Emissions)) +
    geom_bar(stat="identity",fill="grey",width=0.75) + guides(fill=FALSE) +
    labs(x="year", y=expression("Total PM2.5 Emissions")) + 
    labs(title=expression("PM2.5 Vehicle emissions within Baltimore from 1999-2008"))

# Save copy of the plot 5
dev.copy(png, file="plot5.png", height=480, width=480)
dev.off()
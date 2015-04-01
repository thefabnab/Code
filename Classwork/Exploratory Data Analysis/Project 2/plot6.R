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
baltimore_vehicles$city <- "Baltimore"
LA_vehicles <- subset(NEI_vehicles, fips == "06037")
LA_vehicles$city <- "LA"
combined_LA_Baltimore <- rbind(baltimore_vehicles, LA_vehicles)

ggplot(combined_LA_Baltimore, aes(x=factor(year), y=Emissions, fill=city)) +
    geom_bar(aes(fill=year),stat="identity") +
    facet_grid(scales="free", space="free", .~city) + 
    guides(fill=FALSE) + labs(x="year", y=expression("Total PM2.5 Emission")) + 
    labs(title=expression("PM2.5 Vehicle Emissions in Baltimore & LA 1999-2008"))

# Save copy of the plot 6
dev.copy(png, file="plot6.png", height=480, width=480)
dev.off()
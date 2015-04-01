#Establishing WD and libraries
library(data.table)
library(ggplot2)
setwd("Desktop/Work/Google_Drive/School Work/Exploratory Data Analysis/Project 2/exdata-data-NEI_data/")

#reading provided project data
NEI <- readRDS("summarySCC_PM25.rds")
SCC <- readRDS("Source_Classification_Code.rds")

#Subsetting from SCC to find coal sources
CoalCombustionSources <- SCC[SCC$EI.Sector == "Fuel Comb - Comm/Institutional - Coal",]["SCC"]
emissionFromCoal <- NEI[NEI$SCC %in% CoalCombustionSources$SCC, ]
Total_year <- aggregate(Emissions ~ year,emissionFromCoal, sum)
View(head(Total_year))

#Generate plot
ggplot(Total_year,aes(factor(year),Emissions)) +
    geom_bar(stat="identity",fill="grey",width=0.75) + guides(fill=FALSE) +
    labs(x="year", y=expression("Total PM2.5 Emissions")) + 
    labs(title=expression("PM2.5 Coal Combustion Emissions Across US from 1999-2008"))

# Save copy of the plot 4
dev.copy(png, file="plot4.png", height=480, width=480)
dev.off()
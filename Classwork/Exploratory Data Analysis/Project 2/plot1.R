#Establishing WD
library(data.table)
setwd("Desktop/Work/Google_Drive/School Work/Exploratory Data Analysis/Project 2/exdata-data-NEI_data/")
#reading provided project data
NEI <- readRDS("summarySCC_PM25.rds")
SCC <- readRDS("Source_Classification_Code.rds")

#Question number one:
NEI_T <- data.table(NEI)
Total_year <- aggregate(Emissions ~ year,NEI_T, sum)
View(head(Total_year))

#Generate base plot:
barplot(
    Total_year$Emissions,
    names.arg=Total_year$year,
    main="Total PM2.5 Emissions From All US Sources per year",
    xlab="Years",
    ylab="Emissions of PM2.5"
    )

# Save copy of the plot 1
dev.copy(png, file="plot1.png", height=480, width=480)
dev.off()
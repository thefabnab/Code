library(ggplot2)

#load data
data <- read.csv("repdata-data-StormData.csv")

#Subset of data
SpecificCol <- c("EVTYPE", "FATALITIES", "INJURIES", "PROPDMG", "PROPDMGEXP", "CROPDMG", 
           "CROPDMGEXP")
dataSubset <- data[SpecificCol]


#Unique values in column Property damage
unique(dataSubset$PROPDMGEXP)

# Sorting the property exponent data
dataSubset$PROPEXP[dataSubset$PROPDMGEXP == "K"] <- 1000
dataSubset$PROPEXP[dataSubset$PROPDMGEXP == "M"] <- 1e+06
dataSubset$PROPEXP[dataSubset$PROPDMGEXP == ""] <- 1
dataSubset$PROPEXP[dataSubset$PROPDMGEXP == "B"] <- 1e+09
dataSubset$PROPEXP[dataSubset$PROPDMGEXP == "m"] <- 1e+06
dataSubset$PROPEXP[dataSubset$PROPDMGEXP == "0"] <- 1
dataSubset$PROPEXP[dataSubset$PROPDMGEXP == "5"] <- 1e+05
dataSubset$PROPEXP[dataSubset$PROPDMGEXP == "6"] <- 1e+06
dataSubset$PROPEXP[dataSubset$PROPDMGEXP == "4"] <- 10000
dataSubset$PROPEXP[dataSubset$PROPDMGEXP == "2"] <- 100
dataSubset$PROPEXP[dataSubset$PROPDMGEXP == "3"] <- 1000
dataSubset$PROPEXP[dataSubset$PROPDMGEXP == "h"] <- 100
dataSubset$PROPEXP[dataSubset$PROPDMGEXP == "7"] <- 1e+07
dataSubset$PROPEXP[dataSubset$PROPDMGEXP == "H"] <- 100
dataSubset$PROPEXP[dataSubset$PROPDMGEXP == "1"] <- 10
dataSubset$PROPEXP[dataSubset$PROPDMGEXP == "8"] <- 1e+08
# give 0 to invalid exponent data, so they not count in
dataSubset$PROPEXP[dataSubset$PROPDMGEXP == "+"] <- 0
dataSubset$PROPEXP[dataSubset$PROPDMGEXP == "-"] <- 0
dataSubset$PROPEXP[dataSubset$PROPDMGEXP == "?"] <- 0
# compute the property damage value
dataSubset$PROPDMGVAL <- dataSubset$PROPDMG * dataSubset$PROPEXP


#Crop damage
unique(dataSubset$CROPDMGEXP)

# Sorting the property exponent data
dataSubset$CROPEXP[dataSubset$CROPDMGEXP == "M"] <- 1e+06
dataSubset$CROPEXP[dataSubset$CROPDMGEXP == "K"] <- 1000
dataSubset$CROPEXP[dataSubset$CROPDMGEXP == "m"] <- 1e+06
dataSubset$CROPEXP[dataSubset$CROPDMGEXP == "B"] <- 1e+09
dataSubset$CROPEXP[dataSubset$CROPDMGEXP == "0"] <- 1
dataSubset$CROPEXP[dataSubset$CROPDMGEXP == "k"] <- 1000
dataSubset$CROPEXP[dataSubset$CROPDMGEXP == "2"] <- 100
dataSubset$CROPEXP[dataSubset$CROPDMGEXP == ""] <- 1
# give 0 to invalid exponent data, so they not count in
dataSubset$CROPEXP[dataSubset$CROPDMGEXP == "?"] <- 0
# compute the crop damage value
dataSubset$CROPDMGVAL <- dataSubset$CROPDMG * dataSubset$CROPEXP


# aggregate the data by event
fatalities_agg <- aggregate(FATALITIES ~ EVTYPE, data = dataSubset, FUN = sum)
injury_agg <- aggregate(INJURIES ~ EVTYPE, data = dataSubset, FUN = sum)
propdmg_agg <- aggregate(PROPDMGVAL ~ EVTYPE, data = dataSubset, FUN = sum)
cropdmg_agg <- aggregate(CROPDMGVAL ~ EVTYPE, data = dataSubset, FUN = sum)

# get top10 event with highest fatalities
fatal_10 <- fatalities_agg[order(-fatalities_agg$FATALITIES), ][1:10, ]
ggplot(data = fatal_10, aes(x = fatal_10$EVTYPE, y = fatal_10$FATALITIES)) + 
    geom_bar(colour = "black", fill = "blue", stat = "identity") + xlab("Event Type") + 
    ylab("Number of fatalities") + ggtitle("Total number of fatalities, 1950 - 2011") + 
    theme(axis.text.x = element_text(angle = 90, hjust = 1))

# get top10 event with highest injuries
injury_10 <- injury_agg[order(-injury_agg$INJURIES), ][1:10, ]
ggplot(data = injury_10, aes(x = injury_10$EVTYPE, y = injury_10$INJURIES)) + 
    geom_bar(colour = "green", fill = "orange", stat = "identity") + xlab("Event Type") + 
    ylab("Injuries") + ggtitle("Total Injuries by Enviromental Type, 1950 - 2011") + 
    theme(axis.text.x = element_text(angle = 90, hjust = 1))

# get top 10 events with highest property damage
propdmg_10 <- propdmg_agg[order(-propdmg_agg$PROPDMGVAL), ][1:10, ]
ggplot(data = propdmg_10, aes(x = propdmg_10$EVTYPE, y = propdmg_10$PROPDMGVAL)) + 
    geom_bar(colour = "green", fill = "orange", stat = "identity") + xlab("Event Type") + 
    ylab("Billions USD") + ggtitle("Total cost of Property Damage by Enviromental Type, 1950 - 2011") + 
    theme(axis.text.x = element_text(angle = 90, hjust = 1))

# get top 10 events with highest crop damage
cropdmg_10 <- cropdmg_agg[order(-cropdmg_agg$CROPDMGVAL), ][1:10, ]
ggplot(data = cropdmg_10, aes(x = cropdmg_10$EVTYPE, y = cropdmg_10$CROPDMGVAL)) + 
    geom_bar(colour = "green", fill = "orange", stat = "identity") + xlab("Event Type") + 
    ylab("Billions USD") + ggtitle("Total cost of Crop Damage by Enviromental Type, 1950 - 2011") + 
    theme(axis.text.x = element_text(angle = 90, hjust = 1))
#Producing a tidy execution dataset from DeathPenaltyInfo.org
library(data.table)
library(plyr)
library(gcookbook)

#Downloading dataset
download.file("http://www.deathpenaltyinfo.org/exec-xls-export", 
              "execution_data.csv", method = "curl" )

#Reading and setting to data frame
exec <- read.csv("execution_data.csv")
exec <- data.table(exec)

#Utalize setkey to sort data by Race to view incomplete cases
#For some reason complete.cases didn't showup the actual empty cases in the data
setkey(exec, Race)

#Exclude the first 36 incomplete observations
tidy_exec <- exec[37:1438] 

#export tidy exec
write.csv(tidy_exec, file = "tidy_execution.csv")

#Parsed through Date column to produce a year column
year_exec <- mutate(tidy_exec, Year = years(tidy_exec$Date))

#Generated a stacked bar plot demonstrating executions as a function of race
plot <- ggplot(year_exec, aes(x=Year, fill = Race, order = -as.numeric(Race))) +
    geom_bar() + theme(axis.text.x = element_text(angle=90, hjust=1, vjust=.5))+ 
    labs(title = "Executions by Race 1977-2015",x = "Year", y = "Executions") + 
    coord_cartesian(ylim = c(0, 100)) 

#Saved plot in WD
ggsave("Execution_by_race.png")


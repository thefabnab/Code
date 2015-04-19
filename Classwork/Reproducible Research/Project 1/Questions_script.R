#just a quick write up of the process
library(knitr)
library(ggplot2)
data <- read.csv("activity.csv")

#Question No. 1: What is mean total number of steps taken per day?
agg_steps <- aggregate(steps ~ date, data, sum, na.rm = TRUE)

ggplot(agg_steps, aes(steps)) + geom_histogram(colour = "blue", fill = "green") + 
    labs( x = "Number of steps per day", y = "Frequency of times per day" )

Mean_steps <- mean(agg_steps$steps, na.rm = TRUE)
Median_steps <- median(agg_steps$steps, na.rm = TRUE)

#Question No. 2: What is the average daily activity pattern?

agg_pattern <- aggregate(data$steps, by = list(data$interval), FUN = mean, na.rm = TRUE)
colnames(agg_pattern) <- c("interval", "steps")
ggplot(agg_pattern, aes(x=interval, y=steps)) +   
    geom_line(color="blue", size=1) +  
    labs(title="Average Daily Activity Pattern", x="Interval", y="Number of steps")

maximum_interval_step <- agg_pattern[which.max(agg_pattern$steps),]

#Question No. 3:Imputing missing values:

missing_values <- sum(!complete.cases(data))

replace_na <- function(data, val) 
    {
        na_index <- which(is.na(data$steps))
        na_replace <- unlist(lapply(na_index, FUN=function(idx)
            {
                interval = data[idx,]$interval
                val[val$interval == interval,]$steps
            }))
        fill_steps <- data$steps
        fill_steps[na_index] <- na_replace
        fill_steps
    }
replaced_values <- data.frame(steps = replace_na(data, agg_pattern),
                              date = data$date,interval = data$interval)
agg_steps_replaced <- aggregate(steps ~ date, replaced_values, sum, na.rm = TRUE)

ggplot(agg_steps_replaced, aes(x = steps)) + geom_histogram(colour = "blue", fill = "green") + 
    labs( x = "Number of steps per day", y = "Frequency of times per day" )

Mean_steps_replaced <- mean(agg_steps_replaced$steps)
Median_steps_replaced <- median(agg_steps_replaced$steps)

Mean_differences <- abs(Mean_steps_replaced - Mean_steps)
Median_differences <- abs(Median_steps_replaced - Median_steps)

#Question No. 4: Are there differences in activity patterns between weekdays and weekends?

weekdays <- c("Monday", "Tuesday", "Wednesday", "Thursday", 
              "Friday")
replaced_values$dow = as.factor(ifelse(is.element(weekdays(as.Date(replaced_values$date)),
                                               weekdays), "Weekday", "Weekend"))

steps_interval_date <- aggregate(steps ~ interval + dow, replaced_values, mean)

library(lattice)

xyplot(steps_interval_date$steps ~ steps_interval_date$interval|steps_interval_date$dow, 
       main="Average Steps per Day by Interval",xlab="Interval", ylab="Steps",
       layout=c(1,2), type="l")

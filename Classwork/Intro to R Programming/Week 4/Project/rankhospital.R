rankhospital <- function(state, outcome, num = "best") {
    ## Read outcome data
    
    outcomes <- read.csv("rprog-data-ProgAssignment3-data/outcome-of-care-measures.csv", colClasses = "character")
    outcomes[, 11] <- as.numeric(outcomes[, 11])
    outcomes[, 17] <- as.numeric(outcomes[, 17])
    outcomes[, 23] <- as.numeric(outcomes[, 23])
    
    states <- unique(outcomes$State)
    
    if (is.element(state,states)) {
    }
    else {
        stop("invalid state")
    }
    
    if (is.element(outcome,c("heart attack","heart failure","pneumonia"))) {
        if (outcome == "heart attack") {comparator = "Hospital.30.Day.Death..Mortality..Rates.from.Heart.Attack"}
        if (outcome == "heart failure") {comparator = "Hospital.30.Day.Death..Mortality..Rates.from.Heart.Failure"}
        if (outcome == "pneumonia") {comparator = "Hospital.30.Day.Death..Mortality..Rates.from.Pneumonia"}
    }
    else {
        stop("invalid outcome")
    }
    
    all_values <- outcomes[[comparator]]
    
    if (num == "best") {  selected_value <- min(all_values[outcomes$State == state], na.rm=TRUE)
                          chosen_indices <- (all_values == selected_value)
                          chosen_indices <- intersect(which(chosen_indices),which(outcomes$State == state))
                          hospitals_chosen <- outcomes$Hospital.Name[chosen_indices]
                          return(min(hospitals_chosen,na.rm=TRUE))
    }
    if (num == "worst") { selected_value <- max(all_values[outcomes$State == state], na.rm=TRUE) 
                          chosen_indices <- (all_values == selected_value)
                          chosen_indices <- intersect(which(chosen_indices),which(outcomes$State == state))
                          hospitals_chosen <- outcomes$Hospital.Name[chosen_indices]
                          return(min(hospitals_chosen,na.rm=TRUE))
    }
    if (is.numeric(num)) {
        
        indices_of_state <- which(outcomes$State == state)
        values_for_state <- all_values[indices_of_state]
        ordered_values <- values_for_state[order(values_for_state)]
        selected_value <- ordered_values[num]
        
        # See how many of these values there are, and which one of those the one we picked is
        
        indices_with_value <- which(ordered_values == selected_value)
        final_reordered_index = num - indices_with_value[1] + 1
        
        # These indices need to be alphabetically reordered
        
        chosen_indices <- (all_values == selected_value)
        chosen_indices <- intersect(which(chosen_indices),which(outcomes$State == state))
        
        hospitals_chosen <- outcomes$Hospital.Name[chosen_indices]
        ordered_hospitals <- hospitals_chosen[order(hospitals_chosen)]
        return(ordered_hospitals[final_reordered_index])
        
    }
    
}
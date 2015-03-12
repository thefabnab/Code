rankall <- function(outcome, num = "best") {
    
    outcomes <- read.csv("rprog-data-ProgAssignment3-data/outcome-of-care-measures.csv", colClasses = "character")
    outcomes[, 11] <- as.numeric(outcomes[, 11])
    outcomes[, 17] <- as.numeric(outcomes[, 17])
    outcomes[, 23] <- as.numeric(outcomes[, 23])
    
    states <- unique(outcomes$State)
    states <- states[order(states)]
    
    if (is.element(outcome,c("heart attack","heart failure","pneumonia"))) {
        if (outcome == "heart attack") {comparator = "Hospital.30.Day.Death..Mortality..Rates.from.Heart.Attack"}
        if (outcome == "heart failure") {comparator = "Hospital.30.Day.Death..Mortality..Rates.from.Heart.Failure"}
        if (outcome == "pneumonia") {comparator = "Hospital.30.Day.Death..Mortality..Rates.from.Pneumonia"}
    }
    else {
        stop("invalid outcome")
    }
    
    all_values <- outcomes[[comparator]]
    
    hospital_name <- vector()
    for (state in states) {
        indices_of_state <- which(outcomes$State == state)
        values_for_state <- all_values[indices_of_state]
        ordered_values <- values_for_state[order(values_for_state)]
        
        if (num == "best") {selected_index <- 1}
        if (num == "worst") {selected_index <- length(na.omit(ordered_values))}
        if (is.numeric(num)) {selected_index <- num}
        
        selected_value <- ordered_values[selected_index]
        
      
        indices_with_value <- which(ordered_values == selected_value)
        final_reordered_index = selected_index - indices_with_value[1] + 1
        
        
        chosen_indices <- (all_values == selected_value)
        chosen_indices <- intersect(which(chosen_indices),indices_of_state)
        
        hospitals_chosen <- outcomes$Hospital.Name[chosen_indices]
        ordered_hospitals <- hospitals_chosen[order(hospitals_chosen)]
        
        hospital_name <- append(hospital_name, ordered_hospitals[final_reordered_index])
        
    }
    
    hosp_state <- data.frame(hospital=hospital_name,state=states)
    
    return(hosp_state)
    
}
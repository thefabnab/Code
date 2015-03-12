## In the interest of saving computation it can be in ones best interest
## to utalize caching as much as possible. The functions below detail
## how to store a matrix inversion within cache and call upon it when needed

## The makeCacheMatrix function serves to design a matrix that is inversed
## and cached for later use

makeCacheMatrix <- function(x = matrix()) {
  inv <- NULL
  set <- function(y) {
    x <<- y
    inv <<- NULL
  }
  get <- function() x
  setinverse <- function(inverse) inv <<- inverse
  getinverse <- function() inv
  list(set = set, get = get,
       setinverse = setinverse,
       getinverse = getinverse)
  
}

## cacheSolve is a function which retreives the previously cached inversed
## matrix. It first checks if there is such a chache, and if there is it returns
## the desired matrix, if not, it simply calculates the new matrix entered.

cacheSolve <- function(x, ...) {
  inv <- x$getinverse()
  if(!is.null(inv)) {
    message("getting cached data")
    return(inv)
  }
  data <- x$get()
  inv <- solve(data)
  x$setinverse(inv)
  inv
}

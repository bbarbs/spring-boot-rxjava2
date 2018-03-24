## Getting Started
Sample spring boot project which implement [Rxjava2](https://github.com/ReactiveX/RxJava). It uses mainly the [Maybe](http://reactivex.io/RxJava/2.x/javadoc/io/reactivex/Maybe.html) operator for emitting data and to emit single data easily it use **.fromCallable()** method, while **.defer()** return the observer where you need to subcribe.

## Implementation and Exception Handling
* **.fromCallable()**, exception here is thrown and catch by RestControllerAdvice and sent back to user.
```
CustomerServiceImpl.class

public Maybe<Customer> getCustomerById(Long customerId) {
        return Maybe.fromCallable(() -> {
            Customer customer = customerRepository.findOne(customerId);
            if (customer == null) {
                throw new CustomerNotFoundException("Customer not found");
            }
            return customer;
        });
    }
    
=============================================================================================================

CustomerController.class

public Maybe<Customer> getCustomerById(@PathVariable(name = "customerId") Long customerId) {
        return this.customerService.getCustomerById(customerId);
    }

```
* **.defer()**, exception needs to be manage during subcribe in controller and then get the error and throw it so RestControllerAdvice will catch it and sent back to user.
```
CustomerServiceImpl.class

public Maybe<Void> deleteCustomerById(Long customerId) {
        return Maybe.defer(() -> {
            if (!this.customerRepository.exists(customerId)) {
                throw new CustomerNotFoundException("Customer not found");
            }
            this.customerRepository.delete(customerId);
            return Maybe.empty();
        });
    }
    
=============================================================================================================

CustomerController.class

public Maybe<Void> deleteCustomerById(@PathVariable(name = "customerId") Long customerId) {
        AtomicReference<Throwable> cause = new AtomicReference<>();
        Maybe<Void> result = this.customerService.deleteCustomerById(customerId);
        result.subscribe(aVoid -> {}, throwable -> cause.set(throwable));
        if(cause.get() != null) {
            if (cause.get() instanceof CustomerNotFoundException) {
                throw new CustomerNotFoundException(cause.get().getMessage());
            }
        }
        return Maybe.empty();
    }
```

Notes: If subscribe to observable is implemented in .fromCallable() then exception will not be catch by the spring controller advice. It needs to manually throw exception just like in .defer()

## Swagger
* You can check the swagger ui here: http://localhost:8080/swagger-ui.html when running the project.

## Getting Started
Sample spring boot project which implement [Rxjava2](https://github.com/ReactiveX/RxJava). It uses mainly the [Maybe](http://reactivex.io/RxJava/2.x/javadoc/io/reactivex/Maybe.html) operator for emitting data and to emit single data easily it use **.fromCallable()** method, while **.defer()** return the observer where you need to subcribe.

## Implementation
* .fromCallable(), exception here is thrown and catch by RestControllerAdvice.
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
* .defer(), exception needs to be manage during subcribe in controller p.s. i didn't include it here.
```
CustomerServiceImpl.class

public Maybe<List<Customer>> getCustomersByAge(int age) {
        return Maybe.defer(() -> {
            List<Customer> customers = this.customerRepository.findAllByAge(age);
            return Maybe.just(customers);
        });
    }
    
=============================================================================================================

CustomerController.class

public Maybe<List<Customer>> getCustomersByAge(@RequestParam(name = "age") int age) {
        List<Customer> list = new ArrayList<>();
        Maybe<List<Customer>> customers = this.customerService.getCustomersByAge(age);
        customers.subscribe(result -> list.addAll(result));
        return Maybe.just(list);
    }
```

## Swagger
* You can check the swagger ui here: http://localhost:8080/swagger-ui.html when running the project.

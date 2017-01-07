Test repository for Spring Web Reactive + Netty hanging while returning JSON in Windows
---------------------------------------------------------------------------------------

Issue Links:

   * https://jira.spring.io/browse/SPR-15108


This repository is aimed at testing how Netty hangs while trying to return amounts of
JSON greater than 2 Kilobytes in a Spring Boot 2.0 + Spring 5 Web Reactive application,
running on **Windows**.


## How to test

This application uses Spring Boot 2.0 snapshot, and was created using http://start.spring.io with
the *Reactive Web* module enabled.

To compile and run:

```
$ mvn -U clean compile spring-boot:run
```

The application offers a `/list/{size}` URL which returns a JSON array of very simple data items. These
items actually come from a `Flux<Entity>` object returned by the controller, and are created at the
`EntityRepository` class from a `List<Entity>` and then repeated the amount of times specified at the URL.

In order to call it, e.g. with `size = 20`:

```
$ curl http://localhost:8080/list/20
```

## Observed Results

Note this has to be tested on Windows. Specifically, **Windows 10** has been used for these tests. This
works OK in other operating systems.

When the total amount of JSON to be transmitted is below 2 KBytes, it works OK:

```
$ curl http://localhost:8080/list/41 > out
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100  1969    0  1969    0     0  63516      0 --:--:-- --:--:-- --:--:--  128k
```

But when we increase the size, it simply hangs:

```
$ curl http://localhost:8080/list/50 > out1
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100  2017    0  2017    0     0     13      0 --:--:--  0:02:28 --:--:--     0
^C (Interrupted)
```


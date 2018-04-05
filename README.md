# GNetServer
Asynchronous High-performance TCP Server

## Versions
0.1v - Under Development on now

## Goal
make an tcp server structure with netty, spring boot to be used for various purpose(chat, game server)

## Concept

## Usage
*for build executable jar*
```
clean package spring-boot:repackage
```

*application.yaml can make setting easily*
```yaml
# netty configuration
netty:
  port: 10100
  threads:
    worker: 4
    acceptor: 2
  backlog: 100
  statistics:
    report:
      interval: 60000



# logging level
logging:
  level:
    org:
      springframework: info
    io:
      netty: debug
    com:
      gompang: info
```


## Reminder


## References
* [Netty](https://github.com/netty/netty)
* [FlatBuffers](https://github.com/google/flatbuffers)

# TCP-UDP_LatencyMeasurment

Measure the latency and throughput of TCP and/or UDP (as noted below) across at least three pairs of machines using at least two different networks. For example, two CS servers (like wolf and pi), or a CS server to a laptop, wired or wireless, or off-campus. 

- Measure round-trip latency as a function of message size, by sending and receiving (echoing) messages of size 1, 64, and 1024 bytes, using both TCP and UDP. Measure RTTs.

- Measure throughput by sending TCP messages of size 1K, 16K, 64K, 256K, and 1M bytes in each direction. Measure transfer rates.

- Measure the interaction between message size and number of messages using TCP and UDP by sending 1MByte of data (with a 1-byte acknowledgment in the reverse direction) using different numbers of messages: 1024 1024Byte messages, vs 2048 512Byte messages, vs 4096 X 256Byte messages.

Web page with results https://docs.google.com/spreadsheets/d/e/2PACX-1vQSIL4J8kQPMyrB077mHV4ROtWRS161swDj-C8-YdrPKxW54YvxTiWIZUmpyANlP4VwAd8QQgR1Czf-/pubhtml

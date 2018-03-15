## Prject Guide
---

** This project is about the theory behind creating hidden volume.**

I know this method through truecrypt.

---
**Step To Create This Method**

1. create a method that can generate 100 true random words
(as a pseudo-container).
2. Assume that first 50 words contain "fake message", second 50 words contains
"real message".
3. We need to have 2 password and encrypt 2 times.

---

**Note When Implemented:**

* Need offset parameter so it won't write over first created volume.

---
**Implementation**

* Public Method for create volume using true random number generator
* Private Method for expand password x to the size of volume
*


---

**In Reality:**

_Need Sources to support them_

* Some filesystem write throughout the partition (NTFS write on the first sight
	of empty space).
* Need to expand password


---
**Souce:**

[Hidden Encrypted Volumes: keep data safe and secret](https://www.linuxvoice.com/hidden-encrypted-volumes-keep-data-safe-and-secret/)

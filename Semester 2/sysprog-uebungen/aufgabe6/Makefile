#
# Makefile
#

PACKAGES=lib bin
.PHONY: all clean
all clean:
	for p in $(PACKAGES); do \
		(cd $$p && $(MAKE) $@); \
	done
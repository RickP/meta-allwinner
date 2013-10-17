require u-boot.inc

LICENSE = "GPLv2+"

# No patches for other machines yet
COMPATIBLE_MACHINE = "(mele|olinuxino-a13|olinuxino-a20|cubieboard)"

DEFAULT_PREFERENCE_mele          = "1"
DEFAULT_PREFERENCE_olinuxino-a13 = "1"
DEFAULT_PREFERENCE_cubieboard    = "1"
DEFAULT_PREFERENCE_cubieboard2   = "1"
DEFAULT_PREFERENCE_olinuxino-a20 = "1"

SRC_URI = "git://github.com/linux-sunxi/u-boot-sunxi.git;protocol=git;branch=sunxi"

SRCREV = "8a4621c488f33089d831168bfa5bae210a5684c8"
PR = "r7"

LIC_FILES_CHKSUM = "file://README;beginline=1;endline=22;md5=2687c5ebfd9cb284491c3204b726ea29"

S = "${WORKDIR}/git"

PACKAGE_ARCH = "${MACHINE_ARCH}"

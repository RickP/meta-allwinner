require linux.inc

DESCRIPTION = "Linux kernel for Allwinner a10 processors"

KERNEL_IMAGETYPE = "uImage"

COMPATIBLE_MACHINE = "(mele|olinuxino-a13|olinuxino-a20|cubieboard)"

PR = "1"

PV = "3.4.61-r0"
# Last tested version by myself"

MACHINE_KERNEL_PR_append = "a"

SRC_URI += "https://github.com/linux-sunxi/linux-sunxi/archive/sunxi-v${PV}.tar.gz \
        file://defconfig \
        file://screen.conf \
        file://spdif.conf \
        file://sata.conf \
        file://wifi.conf \
        "
SRC_URI[md5sum] = "3cc55e9e6588a2096f6022aea0a05222"

S = "${WORKDIR}/${PN}-sunxi-v${PV}"

do_package_prepend() {

}

do_install_append () {
  install -d ${D}${sysconfdir}/modules-load.d
  install -m 0755 ${WORKDIR}/screen.conf ${D}${sysconfdir}/modules-load.d/screen.conf
  install -m 0755 ${WORKDIR}/spdif.conf ${D}${sysconfdir}/modules-load.d/spdif.conf
  install -m 0755 ${WORKDIR}/sata.conf ${D}${sysconfdir}/modules-load.d/sata.conf
  install -m 0755 ${WORKDIR}/wifi.conf ${D}${sysconfdir}/modules-load.d/wifi.conf
}

#addtask copy_aufs after do_unpack before do_patch

INSANE_SKIP_kernel-dev = "debug-files debug-deps arch"

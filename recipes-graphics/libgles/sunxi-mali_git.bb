DESCRIPTION = "libGLES for the A10/A13 Allwinner processor with Mali 400 (X11)"
LICENSE = "proprietary-binary"

INC_PR = "r2"

LIC_FILES_CHKSUM = "file://README;md5=a103ac69c166fcd98a67a9917dd7affd"

COMPATIBLE_MACHINE = "(mele|cubieboard|cubieboard2|olinuxino-a20)"

DEPENDS = "virtual/libx11 libxau libxdmcp libdrm dri2proto libdri2"

# These libraries shouldn't get installed in world builds unless something
# explicitly depends upon them.
EXCLUDE_FROM_WORLD = "1"
PROVIDES = "virtual/libgles1 virtual/libgles2 virtual/egl"

inherit distro_features_check
REQUIRED_DISTRO_FEATURES = "opengl"

SRCREV_pn-${PN} = "0809383f9d3ee2575da52262a639ddd6464a641f"
SRC_URI = "gitsm://github.com/linux-sunxi/sunxi-mali.git;protocol=http"

S = "${WORKDIR}/git"

# These are closed binaries generated elsewhere so don't check ldflags & text relocations
INSANE_SKIP_${PN} = "ldflags textrel"

do_configure() {
         DESTDIR=${D}/ VERSION=r3p0 ABI=armhf EGL_TYPE=x11 make config
}

do_install() {
             mkdir -p ${D}${libdir}
	     mkdir -p {$D}{includedir}
	     make libdir=${D}${libdir}/ includedir=${D}${includedir}/ install
     	     make libdir=${D}${libdir}/ includedir=${D}${includedir}/ install -C include	     
             # Fix .so name and create symlinks, binary package provides .so wich can't be included directly in package
             rm ${D}${libdir}/libEGL.so.1.4
             ln -sf libMali.so.3 ${D}${libdir}/libEGL.so.1.4
             rm ${D}${libdir}/libGLESv1_CM.so.1.1
             ln -sf libMali.so.3 ${D}${libdir}/libGLESv1_CM.so.1.1
             rm ${D}${libdir}/libGLESv2.so.2.0
             ln -sf  libMali.so.3 ${D}${libdir}/libGLESv2.so.2.0
             mv ${D}${libdir}/libMali.so ${D}${libdir}/libMali.so.3
             ln -sf libMali.so.3.0 ${D}${libdir}/libMali.so
}

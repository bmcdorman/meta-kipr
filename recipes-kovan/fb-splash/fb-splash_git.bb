inherit cmake

SRC_URI = "git://github.com/kipr/fb-splash.git \
	file://logo.raw565 \
	file://fb-splash.service"



S = "${WORKDIR}/git"

COMPATIBLE_MACHINE = "kovan"
SRCREV = "HEAD"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=fe06497acaf4f45999925d348c2605f9"
PR = "8"

EXTRA_OECMAKE = "--no-warn-unused-cli"

OECMAKE_SOURCEPATH = "${S}"

do_install() {
	install -d ${D}/usr/sbin
	install -m 0755 ${S}/deploy/fb-splash ${D}/usr/sbin
	install -d ${D}/etc/fb-splash
	install -m 0755 ${WORKDIR}/logo.raw565 ${D}/etc/fb-splash/
	
	# Service
        install -d ${D}/lib/systemd/system
        install -m 0755 ${WORKDIR}/fb-splash.service ${D}/lib/systemd/system

        install -d ${D}${base_libdir}/systemd/system/basic.target.wants/
        ln -sf ../fb-splash.service ${D}${base_libdir}/systemd/system/basic.target.wants/
}

FILES_${PN} += "${sbindir} /etc /lib"

inherit cmake

SRC_URI = "git://github.com/kipr/kovand.git \
	file://kovand.service \
"

DEPENDS = "libkovan i2c-wrapper"

S = "${WORKDIR}/git"

SRCREV = "HEAD"
LICENSE = "GPL"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=4fe869ee987a340198fb0d54c55c47f1"
PR = "77"

EXTRA_OECMAKE = "--no-warn-unused-cli"

OECMAKE_SOURCEPATH = "${S}"

do_install() {
	install -d ${D}/lib/systemd/system
	install -m 0755 ${WORKDIR}/kovand.service ${D}/lib/systemd/system

	install -d ${D}${base_libdir}/systemd/system/basic.target.wants/
	ln -sf ${WORKDIR}/kovand.service ${D}${base_libdir}/systemd/system/basic.target.wants/

	install -d ${D}/usr/sbin
	install -m 0755 ${S}/deploy/kovand ${D}/usr/sbin/
}

FILES_${PN} = "${bindir} ${sbindir}"
FILES_${PN} += "${base_libdir}/systemd"
FILES_${PN} += "/var/lib/connman"
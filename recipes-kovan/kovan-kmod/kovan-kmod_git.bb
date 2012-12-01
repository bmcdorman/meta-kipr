DESCRIPTION = "Kovan Kernel Module for FPGA communication"
PR = "r0"
SRC_URI = "git://github.com/kipr/kovan-kmod.git"

S = "${WORKDIR}/git"

do_compile () {
	make
}

do_install () {
	install -d ${D}${base_libdir}/modules/
	install -m 0755 ${S}/kovan.ko ${D}${base_libdir}/modules/
}

FILES_${PN} = "${base_libdir}/modules/"
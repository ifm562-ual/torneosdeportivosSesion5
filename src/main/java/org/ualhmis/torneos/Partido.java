package org.ualhmis.torneos;
class Partido {
    private Equipo equipo1;
    private Equipo equipo2;
    private int puntuacionEquipo1;
    private int puntuacionEquipo2;
    
    public Torneo getT() {
		return t;
	}

	public void setT(Torneo t) {
		this.t = t;
	}

	public Instalacion getInstalacionDondeSeJuega() {
		return instalacionDondeSeJuega;
	}

	public void setInstalacionDondeSeJuega(Instalacion instalacionDondeSeJuega) {
		this.instalacionDondeSeJuega = instalacionDondeSeJuega;
	}

	private Torneo t;
    private Instalacion instalacionDondeSeJuega;

    public Partido(Equipo equipo1, Equipo equipo2, Torneo t) {
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.t = t;
    }

    public void registrarResultado(int golesEquipo1, int golesEquipo2) {
        this.puntuacionEquipo1 = golesEquipo1;
        this.puntuacionEquipo2 = golesEquipo2;
    }
    
    public void asignarInstalacion(String tipoInstalacion) throws IllegalArgumentException {
    	boolean sePudoAsignar = false;
    	for(Instalacion iT : this.t.getSede().getInstalaciones())
    	{
        	if(!iT.getEstaOcupada() && iT.getTipo().trim().toLowerCase().equals(tipoInstalacion))
        	{
        		iT.setEstaOcupada(sePudoAsignar = true);
        		this.instalacionDondeSeJuega = iT;
        	}
    	}
    	
    	if(!sePudoAsignar) { throw new IllegalArgumentException("No se pudo asignar, estaba ocupada."); }
    }
    
    public void liberarInstalacion(String tipoInstalacion) {
    	for(Instalacion iT : this.t.getSede().getInstalaciones())
    	{
        	if(iT.getEstaOcupada())
        	{
        		iT.setEstaOcupada(false);
        	}
    	}
    }

	public Equipo getEquipo1() {
		return equipo1;
	}

	public void setEquipo1(Equipo equipo1) {
		this.equipo1 = equipo1;
	}

	public Equipo getEquipo2() {
		return equipo2;
	}

	public void setEquipo2(Equipo equipo2) {
		this.equipo2 = equipo2;
	}

	public int getPuntuacionEquipo1() {
		return puntuacionEquipo1;
	}

	public void setPuntuacionEquipo1(int golesEquipo1) {
		this.puntuacionEquipo1 = golesEquipo1;
	}

	public int getPuntuacionEquipo2() {
		return puntuacionEquipo2;
	}

	public void setPuntuacionEquipo2(int golesEquipo2) {
		this.puntuacionEquipo2 = golesEquipo2;
	}
    
    
}

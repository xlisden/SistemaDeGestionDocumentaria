package com.unu.sistemadegestiondocumentaria.entity;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "sustentaciones")
public class Sustentacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String tesis;

    @ManyToOne()
    @JoinColumn(name = "id_jurado1", foreignKey = @ForeignKey(name = "fk_jurado1_sustentacion"))
    private Administrativo jurado1;

    @ManyToOne()
    @JoinColumn(name = "id_jurado2", foreignKey = @ForeignKey(name = "fk_jurado2_sustentacion"))
    private Administrativo jurado2;

    @ManyToOne()
    @JoinColumn(name = "id_jurado3", foreignKey = @ForeignKey(name = "fk_jurado3_sustentacion"))
    private Administrativo jurado3;

    @ManyToOne()
    @JoinColumn(name = "id_asesor", foreignKey = @ForeignKey(name = "fk_asesor_sustentacion"))
    private Administrativo asesor;
    
    @Transient
    private int idAsesor;
    @Transient
    private int idJurado1;
    @Transient
    private int idJurado2;
    @Transient
    private int idJurado3;

    public Sustentacion() {
    }

    public Sustentacion(Administrativo jurado1, Administrativo jurado2, Administrativo jurado3, Administrativo asesor) {
        this.jurado1 = jurado1;
        this.jurado2 = jurado2;
        this.jurado3 = jurado3;
        this.asesor = asesor;
    }

    public Sustentacion(int id, Administrativo jurado1, Administrativo jurado2, Administrativo jurado3, Administrativo asesor) {
        this.id = id;
        this.jurado1 = jurado1;
        this.jurado2 = jurado2;
        this.jurado3 = jurado3;
        this.asesor = asesor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Administrativo getJurado1() {
        return jurado1;
    }

    public void setJurado1(Administrativo jurado1) {
        this.jurado1 = jurado1;
    }

    public Administrativo getJurado2() {
        return jurado2;
    }

    public void setJurado2(Administrativo jurado2) {
        this.jurado2 = jurado2;
    }

    public Administrativo getJurado3() {
        return jurado3;
    }

    public void setJurado3(Administrativo jurado3) {
        this.jurado3 = jurado3;
    }

    public Administrativo getAsesor() {
        return asesor;
    }

    public void setAsesor(Administrativo asesor) {
        this.asesor = asesor;
    }

	@Override
	public String toString() {
		return "Sustentacion [id=" + id + ", " + (jurado1 != null ? "jurado1=" + jurado1 + ", " : "")
				+ (jurado2 != null ? "jurado2=" + jurado2 + ", " : "")
				+ (jurado3 != null ? "jurado3=" + jurado3 + ", " : "") + (asesor != null ? "asesor=" + asesor : "")
				+ "]";
	}

	public int getIdAsesor() {
		return idAsesor;
	}

	public void setIdAsesor(int idAsesor) {
		this.idAsesor = idAsesor;
	}

	public int getIdJurado1() {
		return idJurado1;
	}

	public void setIdJurado1(int idJurado1) {
		this.idJurado1 = idJurado1;
	}

	public int getIdJurado2() {
		return idJurado2;
	}

	public void setIdJurado2(int idJurado2) {
		this.idJurado2 = idJurado2;
	}

	public int getIdJurado3() {
		return idJurado3;
	}

	public void setIdJurado3(int idJurado3) {
		this.idJurado3 = idJurado3;
	}
    
    

}

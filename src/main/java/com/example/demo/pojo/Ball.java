package com.example.demo.pojo;
import javax.persistence.*;
import java.io.Serializable;
/** 
 * @team mackie Studio 
 * @Author 杰 
 * @Date 2020-11-17 15:33:59 
 */
@Entity
@Table ( name ="color_ball" )
public class Ball  implements Serializable {

	private static final long serialVersionUID =  7439002165075655521L;

	/**
	 * 期数
	 */
	@Id
   	@Column(name = "id" )
    @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	/**
	 * 红球1
	 */
   	@Column(name = "red1" )
	private Long red1;

	/**
	 * 红球2
	 */
   	@Column(name = "red2" )
	private Long red2;

	/**
	 * 红球3
	 */
   	@Column(name = "red3" )
	private Long red3;

	/**
	 * 红球4
	 */
   	@Column(name = "red4" )
	private Long red4;

	/**
	 * 红球5
	 */
   	@Column(name = "red5" )
	private Long red5;

	/**
	 * 红球6
	 */
   	@Column(name = "red6" )
	private Long red6;

	/**
	 * 篮球
	 */
   	@Column(name = "blue" )
	private Long blue;

    /**
     * 是否出现过;0 -->没出现; 1 -->出现过;
     */
    @Column(name = "appear" )
    private Long appear;

    public Ball() {
    }

    public Ball(Long red1, Long red2, Long red3, Long red4, Long red5, Long red6, Long blue,Long appear) {
        this.red1 = red1;
        this.red2 = red2;
        this.red3 = red3;
        this.red4 = red4;
        this.red5 = red5;
        this.red6 = red6;
        this.blue = blue;
        this.appear = appear;
    }

    public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public Long getRed1() {
    return red1;
  }

  public void setRed1(Long red1) {
    this.red1 = red1;
  }


  public Long getRed2() {
    return red2;
  }

  public void setRed2(Long red2) {
    this.red2 = red2;
  }


  public Long getRed3() {
    return red3;
  }

  public void setRed3(Long red3) {
    this.red3 = red3;
  }


  public Long getRed4() {
    return red4;
  }

  public void setRed4(Long red4) {
    this.red4 = red4;
  }


  public Long getRed5() {
    return red5;
  }

  public void setRed5(Long red5) {
    this.red5 = red5;
  }


  public Long getRed6() {
    return red6;
  }

  public void setRed6(Long red6) {
    this.red6 = red6;
  }


  public Long getBlue() {
    return blue;
  }

  public void setBlue(Long blue) {
    this.blue = blue;
  }

}

package domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class SessionId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idPlayer;
	private Integer idQuiz;
	private Date date;

	public SessionId() {
	}

	public SessionId(Integer idPlayer, Integer idQuiz, Date date) {
		this.idPlayer = idPlayer;
		this.idQuiz = idQuiz;
		this.date = date;
	}

	public Integer getIdPlayer() {
		return idPlayer;
	}

	public void setIdPlayer(Integer idPlayer) {
		this.idPlayer = idPlayer;
	}

	public Integer getIdQuiz() {
		return idQuiz;
	}

	public void setIdQuiz(Integer idQuiz) {
		this.idQuiz = idQuiz;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result
				+ ((idPlayer == null) ? 0 : idPlayer.hashCode());
		result = prime * result + ((idQuiz == null) ? 0 : idQuiz.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SessionId other = (SessionId) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (idPlayer == null) {
			if (other.idPlayer != null)
				return false;
		} else if (!idPlayer.equals(other.idPlayer))
			return false;
		if (idQuiz == null) {
			if (other.idQuiz != null)
				return false;
		} else if (!idQuiz.equals(other.idQuiz))
			return false;
		return true;
	}

}

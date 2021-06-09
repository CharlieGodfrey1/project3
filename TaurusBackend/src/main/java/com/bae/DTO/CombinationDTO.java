package com.bae.DTO;

import java.util.Set;

public class CombinationDTO {

	private SuspectDTO suspect;
	private Set<ObservationDTO> obs;

	public CombinationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CombinationDTO(SuspectDTO suspect, Set<ObservationDTO> obs) {
		super();
		this.suspect = suspect;
		this.obs = obs;
	}

	public SuspectDTO getSuspect() {
		return suspect;
	}

	public void setSuspect(SuspectDTO suspect) {
		this.suspect = suspect;
	}

	public Set<ObservationDTO> getObs() {
		return obs;
	}

	public void setObs(Set<ObservationDTO> obs) {
		this.obs = obs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((obs == null) ? 0 : obs.hashCode());
		result = prime * result + ((suspect == null) ? 0 : suspect.hashCode());
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
		CombinationDTO other = (CombinationDTO) obj;
		if (obs == null) {
			if (other.obs != null)
				return false;
		} else if (!obs.equals(other.obs))
			return false;
		if (suspect == null) {
			if (other.suspect != null)
				return false;
		} else if (!suspect.equals(other.suspect))
			return false;
		return true;
	}

}

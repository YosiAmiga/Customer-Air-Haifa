package entity;

import utils.Role;

public class GroundAttendantsInShift {

	private GroundAttendant groundAttendant;
	private Shift shift;
	private Role role;
	
	/**
	 * @param groundAttendant
	 * @param shift
	 * @param role
	 */
	
	public GroundAttendantsInShift(GroundAttendant groundAttendant, Shift shift, Role role) {
		super();
		this.groundAttendant = groundAttendant;
		this.shift = shift;
		this.role = role;
	}

	public GroundAttendant getGroundAttendant() {
		return groundAttendant;
	}

	public void setGroundAttendant(GroundAttendant groundAttendant) {
		this.groundAttendant = groundAttendant;
	}

	public Shift getShift() {
		return shift;
	}

	public void setShift(Shift shift) {
		this.shift = shift;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((groundAttendant == null) ? 0 : groundAttendant.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((shift == null) ? 0 : shift.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof GroundAttendantsInShift))
			return false;
		GroundAttendantsInShift other = (GroundAttendantsInShift) obj;
		if (groundAttendant == null) {
			if (other.groundAttendant != null)
				return false;
		} else if (!groundAttendant.equals(other.groundAttendant))
			return false;
		if (role != other.role)
			return false;
		if (shift == null) {
			if (other.shift != null)
				return false;
		} else if (!shift.equals(other.shift))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GroundAttendantsInShift [groundAttendant=" + groundAttendant + ", shift=" + shift + ", role=" + role
				+ "]";
	}
	
	
	

}

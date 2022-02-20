package entity;

import utils.PieceType;

public class EntertainmentPiece {
	
	private int pieceID;
	private String pieceName;
	private String description;
	private PieceType Type;
	private String Type2;
	
	//for query
	public EntertainmentPiece(String pieceName, String description, String type) {
		super();
		this.pieceName = pieceName;
		this.description = description;
		this.Type2 = type;
	}
	
	public EntertainmentPiece(int pieceID, String pieceName, String description, String type) {
		super();
		this.pieceID = pieceID;
		this.pieceName = pieceName;
		this.description = description;
		this.Type2 = type;
	}
	/**
	 * @param pieceID
	 * @param pieceName
	 * @param description
	 * @param type
	 */
	public EntertainmentPiece(int pieceID, String pieceName, String description, PieceType type) {
		super();
		this.pieceID = pieceID;
		this.pieceName = pieceName;
		this.description = description;
		Type = type;
	}
	public int getPieceID() {
		return pieceID;
	}
	public void setPieceID(int pieceID) {
		this.pieceID = pieceID;
	}
	public String getPieceName() {
		return pieceName;
	}
	public void setPieceName(String pieceName) {
		this.pieceName = pieceName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public PieceType getType() {
		return Type;
	}
	public void setType(PieceType type) {
		Type = type;
	}
	public String getType2() {
		return Type2;
	}
	public void setType2(String type2) {
		Type2 = type2;
	}
	@Override
	public String toString() {
		return "EntertainmentPiece [pieceID=" + pieceID + ", pieceName=" + pieceName + ", description=" + description
				+ ", Type=" + Type + "]";
	}


}

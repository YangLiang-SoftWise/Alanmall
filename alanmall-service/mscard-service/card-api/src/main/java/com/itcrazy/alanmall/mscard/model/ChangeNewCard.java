package com.itcrazy.alanmall.mscard.model;

public class ChangeNewCard extends CardBaseModel{
		// 新卡卡号
		private String newCardNo;

		// 新卡卡类别
		private String newName;

		public String getNewCardNo() {
			return newCardNo;
		}

		public void setNewCardNo(String newCardNo) {
			this.newCardNo = newCardNo;
		}

		public String getNewName() {
			return newName;
		}

		public void setNewName(String newName) {
			this.newName = newName;
		}
}

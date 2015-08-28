package com.example.noteproject;

import java.sql.Date;

public class NoteItem {
	int id;
	String title, content;
	String created, modified;
	int alarm;
	String remind;

	public NoteItem(int id, String title,String content,String created,String modified,int alarm,String remind)
	{
		this.id=id;
		this.title=title;
		this.content=content;
		this.created=created;
		this.modified=modified;
		this.alarm=alarm;
		this.remind=remind;
	}
	public NoteItem()
	{}
	public void setId(int id) {
		this.id = id;
	}

	public void setInfo(String title, String content, String created,
			int alarm) {
		this.title = title;
		this.content = content;
		this.created = created;
		this.alarm = alarm;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}
	public void setRemind(String remind)
	{
		this.remind=remind;
	}
	
	public String getRemind()
	{
		return this.remind;
	}
	public int getId() {
		return this.id;
	}

	public String getTitle() {
		return this.title;
	}

	public String getContent() {
		return this.content;
	}
	public String getCreated()
	{
		return this.created;
	}
	public String getModified()
	{
		return this.modified;
	}
	public int getAlarm()
	{
		return this.alarm;
	}
}

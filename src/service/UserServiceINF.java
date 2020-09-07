package service;

import DTO.Board;

public interface UserServiceINF {
	
	public void join();
	public void login();
	public void logout();
	public void changeNick();
	public void withdrawal();
	public void myPost();
	
	public void posting();
	public void postList();
	public void searchPost();
	public Board readPost(int readNum);
	public void modifyPost(Board readPost);
	public void deletePost(Board readPost);
	public void boomUp(int postNo);
	
}

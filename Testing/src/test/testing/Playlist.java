package test.testing;

/*
 * Implementation example of Floyd's cycle-finding algorithm to find the first loop in a chain efficiently 
 * 
 * our playlist is composed of multiple songs one pointing to the other and we want to know if the playlist 
 * is a repeating one or not without consuming much time or space
 * 
 * for more check wiki : https://en.wikipedia.org/wiki/Cycle_detection#Tortoise_and_hare
 */

public class Playlist {
	
    public static class Song {
        private String name;
        private Song nextSong;
 
        public Song(String name) {
            this.name = name;
        }
    
        public void setNextSong(Song nextSong) {
            this.nextSong = nextSong;
        }
    
        public boolean isRepeatingPlaylist() {
        	
        	if(this.nextSong == null) {
        		
        		return false;
        	} 
        	
        	Song tortoise = this.nextSong;
        	Song hare = this.nextSong;
        	
        	while(true) {
        		tortoise = tortoise.nextSong;
            	
            	if(hare.nextSong != null) {
            		hare = hare.nextSong.nextSong;
            	} else {
            		return false;
            	}
            	
            	if( hare == null || tortoise == null ) {
            		return false;
            	}
            	
            	if( hare.name.equalsIgnoreCase(tortoise.name) ) {
            		return true;
            	}
        	}
        }
    }
    
    public static void main(String[] args) {
        Song first = new Song("Hello");
        Song second = new Song("Eye of the tiger");
    
        first.setNextSong(second);
        second.setNextSong(first);
    
        System.out.println(first.isRepeatingPlaylist());
    }
}

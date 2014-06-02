package edu.uci.ics.luci.groupstatusserver.statusdatabase;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mortbay.log.Log;

import com.google.appengine.api.datastore.Text;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import edu.uci.ics.luci.groupstatusserver.statusdatabase.StatusDAO;
import edu.uci.ics.luci.groupstatusserver.userdatabase.UserDAO;

@SuppressWarnings("serial")
public class ServletCreateFooStatuses extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("Creating Foo statuses ");

		String statusPool[]= getStatusFromPoems();
		String groupStatusPool[]= getGroupStatusFromPoems();
		String userID = "";
		String group = "";
		String timestamp = "";
		String status = "";
		String groupStatus = "";
		Text wifiList = new Text("Foo wifiLists"); //String must be 500 characters or less.
		String noiseLevel = "Foo noiseLevel";
		String location = "Foo location";
		String address = "Foo address";
		
//		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd HH:mm:ss");
//		Date dt = new Date();
//		timestamp = sdf.format(dt); // formats to 06/02 09:10:18
		
		String timestampPool[] = {
				"06/02 09:52:09",
				"06/02 12:27:28",
				"06/02 15:35:44",
				"06/02 19:20:13",
				"06/02 21:16:12",}; 
		
		Random rand = new Random();

		for(int i=0;i<3;i++){
			group="Foo group 00" + (i+1);  
			for(int j=0;j<5;j++){
				userID=Integer.toString(9000 + i*10 + j);
				for(int k=0;k<5;k++){
					timestamp=timestampPool[k];
					status = statusPool[rand.nextInt(statusPool.length)];
					groupStatus = groupStatusPool[rand.nextInt(groupStatusPool.length)];
					StatusDAO.INSTANCE.add(userID, group, timestamp, status, groupStatus, wifiList, noiseLevel, location, address);
				}
			}
		}
		
		

		resp.sendRedirect("/ParticipantManagementApplication.jsp");
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("doGet @ ServletCreateTodo");

		doPost(req, resp);

	}

	private String[] getStatusFromPoems() {
		String s[]={
				"I am afraid to own a Body",
				"I am alive - I guess",
				"I am ashamed - I hide",
				"I asked no other thing",
				"I bet with every Wind that blew",
				"I breathed enough to take the Trick",
				"I bring an unaccustomed wine",
				"I Came to buy a smile - today",
				"I can wade Grief",
				"I can't tell you - but you feel it",
				"I cannot be ashamed",
				"I cannot buy it - 'tis not sold",
				"I cannot dance upon my Toes",
				"I cannot live with You",
				"I cannot meet the Spring unmoved",
				"I cannot see my soul but know 'tis there",
				"I cannot want it more",
				"I cautious, scanned my little life",
				"I could bring You Jewels - had I a mind to",
				"I could die - to know",
				"I could not drink it, Sweet",
				"I could not prove the Years had feet",
				"I could suffice for Him, I knew",
				"I cried at Pity - not at Pain",
				"I cross till I am weary",
				"I did not reach Thee",
				"I died for Beauty - but was scarce",
				"I do not care - why should I care",
				"I dreaded that first Robin, so",
				"I dwell in Possibility",
				"I envy Seas, whereon He rides",
				"I fear a Man of frugal Speech",
				"I felt a Cleaving in my Mind",
				"I felt a Funeral, in my Brain",
				"I felt my life with both my hands",
				"I fit for them",
				"I found the words to every thought",
				"I gained it so",
				"I gave myself to Him",
				"I got so I could take his name",
				"I groped for him before I knew",
				"I had a daily Bliss",
				"I had a guinea golden",
				"I had been hungry, all the Years",
				"I had no Cause to be awake",
				"I had no time to Hate",
				"I had not minded - Walls",
				"I had some things that I called mine",
				"I had the Glory - that will do",
				"I have a Bird in spring",
				"I have a King, who does not speak",
				"I have never seen Volcanoes",
				"I have no Life but this",
				"I haven't told my garden yet",
				"I heard a Fly buzz - when I died",
				"I heard, as if I had no Ear",
				"I held a Jewel in my fingers",
				"I held it so tight that I lost it",
				"I hide myself within my flower",
				"I keep my pledge.",
				"I knew that I had gained",
				"I know a place where Summer strives",
				"I know lives, I could miss",
				"I know of people in the Grave",
				"I know some lonely Houses off the Road",
				"I know Suspense - it steps so terse",
				"I know that He exists.",
				"I know where Wells grow - Droughtless Wells",
				"I learned - at least - what Home could be",
				"I like a look of Agony",
				"I like to see it lap the Miles",
				"I live with Him - I see His face",
				"I lived on Dread",
				"I lost a World - the other day!",
				"I made slow Riches but my Gain",
				"I make His Crescent fill or lack",
				"I many times thought Peace had come",
				"I meant to find Her when I came",
				"I meant to have but modest needs",
				"I measure every Grief I meet",
				"I met a King this afternoon!",
				"I never felt at Home - Below",
				"I never hear that one is dead",
				"I never hear the word escape",
				"I never lost as much but twice",
				"I never saw a Moor",
				"I never told the buried gold",
				"I noticed People disappeared",
				"I often passed the village",
				"I pay - in Satin Cash",
				"I play at Riches - to appease",
				"I prayed, at first, a little Girl",
				"I read my sentence - steadily",
				"I reason, Earth is short",
				"I reckon - when I count it all",
				"I robbed the Woods",
				"I rose - because He sank",
				"I saw no Way - The Heavens were stitched",
				"I saw that the Flake was on it",
				"I saw the wind within her",
				"I see thee better - in the Dark",
				"I see thee clearer for the Grave",
				"I send Two Sunsets",
				"I send you a decrepit flower",
				"I shall keep singing!",
				"I shall know why - when Time is over",
				"I shall not murmur if at last",
				"I should have been too glad, I see",
				"I should not dare to be so sad",
				"I should not dare to leave my friend",
				"I showed her Heights she never saw",
				"I sing to use the Waiting",
				"I sometimes drop it, for a Quick",
				"I started Early - Took my Dog",
				"I stepped from Plank to Plank",
				"I stole them from a Bee",
				"I sued the News - yet feared - the News",
				"I suppose the time will come",
				"I taste a liquor never brewed",
				"I tend my flowers for thee",
				"I think I was enchanted",
				"I think just how my shape will rise",
				"I think that the Root of the Wind is Water",
				"I think the Hemlock likes to stand",
				"I think the longest Hour of all",
				"I think to Live - may be a Bliss",
				"I thought that nature was enough",
				"I thought the Train would never come",
				"I tie my Hat - I crease my Shawl",
				"I took my Power in my Hand",
				"I took one Draught of Life",
				"I tried to think a lonelier Thing",
				"I want - it pleaded - All its life",
				"I was a Phoebe - nothing more",
				"I was the slightest in the House",
				"I watched her face to see which way",
				"I watched the Moon around the House",
				"I went to Heaven",
				"I went to thank Her",
				"I worked for chaff and earning Wheat",
				"I would distil a cup",
				"I would not paint - a picture",
				"I Years had been from Home",
				"I'd rather recollect a setting",
				"I'll clutch - and clutch",
				"I'll send the feather from my Hat!",
				"I'll tell you how the Sun rose",
				"I'm ceded - I've stopped being Theirs",
				"I'm Nobody! Who are you?",
				"I'm saying every day",
				"I'm sorry for the Dead - Today",
				"I'm the little Heart's Ease!",
				"I'm wife - I've finished that",
				"I've dropped my Brain - My Soul is numb",
				"I've got an arrow here.",
				"I've heard an Organ talk, sometimes",
				"I've known a Heaven, like a Tent",
				"I've none to tell me to but Thee",
				"I've nothing else - to bring, You know",
				"I've seen a Dying Eye"
		};
		return s;
	}
	private String[] getGroupStatusFromPoems() {
		String s[]={
				"We - Bee and I - live by the quaffing",
				"We can but follow to the Sun",
				"We Cover Thee - Sweet Face",
				"We do not know the time we lose",
				"We do not play on Graves",
				"We don't cry - Tim and I",
				"We dream - it is good we are dreaming",
				"We grow accustomed to the Dark",
				"We introduce ourselves",
				"We knew not that we were to live",
				"We learn it in Retreating",
				"We learned the Whole of Love",
				"We like a Hairbreadth 'scape",
				"We like March.",
				"We lose - because we win",
				"We met as Sparks - Diverging Flints",
				"We miss a Kinsman more",
				"We miss Her, not because We see",
				"We never know how high we are",
				"We never know we go when we are going",
				"We outgrow love, like other things",
				"We play at Paste",
				"We pray - to Heaven",
				"We see - Comparatively",
				"We send the Wave to find the Wave",
				"We shall find the Cube of the Rainbow.",
				"We should not mind so small a flower",
				"We shun because we prize her Face",
				"We shun it ere it comes",
				"We talked as Girls do",
				"We talked with each other about each other",
				"We thirst at first - 'tis Nature's Act",
				"We wear our sober Dresses when we die",
				"We'll pass without the parting",
				"Went up a year this evening!"
		};
		return s;
	}
}
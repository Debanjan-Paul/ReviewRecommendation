package main;
import java.io.*;
import java.util.*;
import java.math.*;

import support.Reviews;
public class Greedy {

	public static void main(String args[])throws IOException{
		Greedy ob=new Greedy();
		//String path="E:\\work\\Deepanshu\\DummyData4.txt"; //Set the path for "postive.txt" file for initial positive opinion words
		String path=args[0];
		BufferedReader inp= new BufferedReader(new FileReader(path));
		int m=0,n=0;
		String sCurrentLine= inp.readLine();
		String dataTemp[]=sCurrentLine.split(" ");
		m=dataTemp.length;
		while((sCurrentLine = inp.readLine()) != null){
			
			n++;
		}
		System.out.println(n+1+" "+m);
		int data1[][]=new int[n+1][m];
		BufferedReader inp1= new BufferedReader(new FileReader(path));
		int count=0;
		while(count++<=n){
			sCurrentLine = inp1.readLine();
			StringTokenizer st1 = new StringTokenizer(sCurrentLine);
			for(int i=0;i<m;i++)
				data1[count-1][i]=Integer.parseInt(st1.nextToken());
		}
		
		for(int i=0;i<=n;i++){
			System.out.print(i+1+" ");
			for(int j=0;j<m;j++)
				System.out.print(data1[i][j]);
			System.out.println();
		}
		
		double MeanSet[]=new double[m];
		
		for(int i=0;i<m;i++){
			count=0;
			for(int j=0;j<=n;j++)
			if(data1[j][i]==1)
					count++;
			MeanSet[i]=(double)count/(n+1);
		}
		for(int i=0;i<m;i++)
			System.out.print((int)(MeanSet[i]*100)+"% ");
		System.out.println();
		
		Reviews obj[]=new Reviews[n+1];
		for(int i=0;i<=n;i++){
			
			int freq=0;
			for(int j=0;j<m;j++)
				freq+=data1[i][j];
			obj[i]=new Reviews(i+1,freq);
		}
		
		
		
		
		
		
		int avg=0;
		for(int i=0;i<=n;i++){
			System.out.println(obj[i].reviewId+" "+obj[i].freq);
			avg+=obj[i].freq;
		}
		avg/=(n+1);int countOfReviews=0;
		System.out.println(avg);
		for(int i=0;i<=n;i++){
			if(obj[i].freq>=avg){
			System.out.println(obj[i].reviewId+" "+obj[i].freq);
			countOfReviews++;
			}
			
		}
		int data[][]=new int[countOfReviews][m+1];
		int poi=-1;
		for(int i=0;i<=n;i++){
			if(obj[i].freq>=avg){
				data[++poi][0]=obj[i].reviewId;
				for(int j=1;j<=m;j++){
					data[poi][j]=data1[i][j-1];
				}
			}
		}
		
		for(int i=0;i<countOfReviews;i++){
			System.out.print(data[i][0]);
			for(int j=1;j<=m;j++)
				System.out.print(data[i][j]);
			System.out.println();
		}
		//end of input
		//calculation of TAO-Mean Set-->>MeanSet
		 MeanSet=new double[m];
		
		for(int i=0;i<m;i++){
			count=0;
			for(int j=0;j<countOfReviews;j++)
			if(data[j][i+1]==1)
					count++;
			MeanSet[i]=(double)count/(n+1);
		}
		for(int i=0;i<m;i++)
			System.out.print((int)(MeanSet[i]*100)+"% ");
		
	//end
		//Input K
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the value of K");
		int K=Integer.parseInt(br.readLine());
		//end
		//Algorithm
		int SET[][]=new int[K][m+1];
		for(int i=0;i<K;i++)
			for(int j=0;j<m+1;j++)
				SET[i][j]=0;
		double TempSet[]=new double[m];
		int val=0;int pos=-1;
		while(val<K){
			double l2DistMin=999999999;int reviewidcount=0;int pos1=-1;
			for(reviewidcount=0;reviewidcount<countOfReviews;reviewidcount++){//all reviews
				int reviewid=data[reviewidcount][0];
				int flag=0;
				for(int i=0;i<val;i++)
					if(SET[i][0]==reviewid)
						flag=1;
					
				if(flag==0){
				if(val>0){
				for(int i=1;i<=m;i++){
					count=0;
					for(int j=0;j<val;j++)
					count+=SET[j][i];
					count+=data[reviewidcount][i];
					TempSet[i-1]=(double)count/(val+1);
					}
			    }
				else{//for val=0
				for(int i=1;i<=m;i++)
				TempSet[i-1]=data[reviewidcount][i];
				}
				double l2Dist=0;
				for(int i=0;i<m;i++)
					l2Dist+=Math.pow((TempSet[i]-MeanSet[i]),2);
				if(l2Dist<l2DistMin){
					l2DistMin=l2Dist;
					pos=reviewid;
					pos1=reviewidcount;
				}
				
			}
			
			}
			SET[val][0]=pos;
			for(int i=1;i<=m;i++)
				SET[val][i]=data[pos1][i];
			val++;
			
		}
		
		for(int i=0;i<K;i++){
			for(int j=0;j<m+1;j++)
				System.out.print( SET[i][j]+" ");
			System.out.println(i);
		}
		//System.out.println(i);
		//evaluate
		for(int i=1;i<m;i++){
			count=0;
			for(int j=0;j<K;j++)
				count+=SET[j][i];
			MeanSet[i-1]=(double)count/(K);
		}
		for(int i=0;i<m;i++)
			System.out.print((int)(MeanSet[i]*100)+"% ");
		//end
	}
	
}

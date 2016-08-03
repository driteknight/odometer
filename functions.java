public static long nextReading(long number,ArrayList<long> readings){
	
	int i = 0;
	while( i < readings.size()){
		long num = readings.get(i);
		if( number == num ){
			if(i == readings.size()-1){
				return readings.get(0);
			}
			return readings.get(i+1);
		}
	}
}

for(int i : readings)

public class MyBase64
{
	private static String table = "";
	
	static
	{
		for ( int i = 65 ; i <= 90 ; i++ )
			table += ( char ) i;
		
		for ( int i = 97 ; i <= 122 ; i++ )
			table += ( char ) i;
		
		for ( int i = 48 ; i <= 57 ; i++ )
			table += ( char ) i;
		
		table += '+';
		table += '/';
	}
	
	public static String encrypt ( String plainText )
	{
		String result = "";
		
		String currentGroup;
		
		int i;
		for ( i = 0; i + 3 <= plainText.length () ; i += 3 )
		{
			currentGroup = getBinaryString ( plainText.substring ( i , i + 3 ) );
			
			result += table.charAt ( getIndexOf ( currentGroup.substring ( 0 , 6 ) ) );
			result += table.charAt ( getIndexOf ( currentGroup.substring ( 6 , 12 ) ) );
			result += table.charAt ( getIndexOf ( currentGroup.substring ( 12 , 18 ) ) );
			result += table.charAt ( getIndexOf ( currentGroup.substring ( 18 , 24 ) ) );
		}
		
		if ( i + 2 <= plainText.length () )
		{
			currentGroup = getBinaryString ( plainText.substring ( i , i + 2 ) );
			currentGroup += "00";
			
			result += table.charAt ( getIndexOf ( currentGroup.substring ( 0 , 6 ) ) );
			result += table.charAt ( getIndexOf ( currentGroup.substring ( 6 , 12 ) ) );
			result += table.charAt ( getIndexOf ( currentGroup.substring ( 12 , 18 ) ) );
			result += "=";
			
			i += 2;
		}
		
		if ( i + 1 <= plainText.length () )
		{
			currentGroup = getBinaryString ( plainText.substring ( i , i + 1 ) );
			currentGroup += "0000";
			
			result += table.charAt ( getIndexOf ( currentGroup.substring ( 0 , 6 ) ) );
			result += table.charAt ( getIndexOf ( currentGroup.substring ( 6 , 12 ) ) );
			result += "==";
		}
		
		return result;
	}
	
	public static String decrypt ( String base64 )
	{
		String result = "";
		
		String currentGroup;
		
		int i;
		for ( i = 0; i + 4 <= base64.length () ; i += 4 )
		{
			currentGroup = decryptGetBinaryString ( base64.substring ( i , i + 4 ) );
			
			result += ( char ) decryptGetIndexOf ( currentGroup.substring ( 0 , 8 ) );
			
			if ( base64.charAt ( i + 2 ) == '=' )
				break;
			
			result += ( char ) decryptGetIndexOf ( currentGroup.substring ( 8 , 16 ) );
			
			if ( base64.charAt ( i + 3 ) == '=' )
				break;
			
			result += ( char ) decryptGetIndexOf ( currentGroup.substring ( 16 , 24 ) );
		}
		
		return result;
	}
	
	private static int getIndexOf ( String text )
	{
		int value = 0;
		
		for ( int j = 0 ; j < 6 ; j++ )
			value += ( text.charAt ( j ) == '1' ? 1 : 0 ) * Math.pow ( 2 , 5 - j );
		
		return value;
	}
	
	private static int decryptGetIndexOf ( String text )
	{
		int value = 0;
		
		for ( int j = 0 ; j < 8 ; j++ )
			value += ( text.charAt ( j ) == '1' ? 1 : 0 ) * Math.pow ( 2 , 7 - j );
		
		return value;
	}
	
	private static String getBinaryString ( String text )
	{
		String result = "";
		
		for ( int i = 0 ; i < text.length () ; i++ )
			result += fixLeftPadding ( Integer.toBinaryString ( ( int ) text.getBytes ()[ i ] ) );
		
		return result;
	}
	
	private static String decryptGetBinaryString ( String text )
	{
		String result = "";
		
		for ( int i = 0 ; i < text.length () ; i++ )
			result += decryptFixLeftPadding ( Integer.toBinaryString ( table.indexOf ( text.charAt ( i ) ) ) );
		
		return result;
	}
	
	private static String fixLeftPadding ( String text )
	{
		while ( text.length () < 8 )
			text = "0".concat ( text );
		
		return text;
	}
	
	private static String decryptFixLeftPadding ( String text )
	{
		while ( text.length () < 6 )
			text = "0".concat ( text );
		
		return text;
	}
}
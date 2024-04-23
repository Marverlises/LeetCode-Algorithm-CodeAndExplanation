package week2;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

/**
 * @Author Baiyu
 * @Time 2024/1/26 13:12
 * @StudentNumber 2018217662
 * @Description
 */
public class SolutionTest {
    Solution1 solution1 = new Solution1();
    
    @Test
    public void lengthOfLastWord_returnsLengthOfLastWord() {
        assertEquals(5, solution1.lengthOfLastWord("Hello World"));
        assertEquals(0, solution1.lengthOfLastWord(""));
        assertEquals(3, solution1.lengthOfLastWord("   one   "));
    }
    
    @Test
    public void lengthOfLastWord2_returnsLengthOfLastWordIgnoringTrailingSpaces() {
        assertEquals(5, solution1.lengthOfLastWord2("Hello World"));
        assertEquals(0, solution1.lengthOfLastWord2(""));
        assertEquals(3, solution1.lengthOfLastWord2("   one   "));
    }
    
    @Test
    public void longestCommonPrefix_returnsLongestCommonPrefix() {
        assertEquals("fl", solution1.longestCommonPrefix(new String[]{"ab", "a"}));
    }
    
    @Test
    public void reverseWords() {
        assertEquals("fl", Solution2.reverseWords("a good   example"));
    }
    @Test
    public void convert_returnsConvertedStringWhenNumRowsIsThree() {
        assertEquals("Hoelel", Solution3.convert("Hello", 3));
    }
    
    @Test
    public void convert_returnsEmptyStringWhenInputIsEmpty() {
        assertEquals("", Solution3.convert("PAYPALISHIRING", 3));
    }
    
    @Test
    public void convert_returnsConvertedStringWhenNumRowsIsGreaterThanStringLength() {
        assertEquals("Hello", Solution3.convert("N", 1));
    }
    
    @Test
    public void strStr_returnsZeroWhenNeedleIsEmpty() {
        assertEquals(0, Solution4.strStr3("sadbutsad", "sad"));
    }
    
    @Test
    public void strStr2_returnsCorrectIndexWhenPatternIsAtStart() {
        assertEquals(0, Solution4.strStr3("mumumAmubm", "mumumubm"));
    }
    

}

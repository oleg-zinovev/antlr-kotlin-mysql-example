import org.antlr.v4.kotlinruntime.*
import org.junit.Assert
import org.junit.Test

/**
 * @author ozinoviev
 * @since 08.10.18
 */
class MySqlParserTest {
    @Test
    fun test_bitrix_queries_cut() {
        val stream = CharStreams.fromStream(this.javaClass.classLoader.getResourceAsStream("bitrix_queries_cut.sql"));
        val lexer = MySqlLexer(stream)
        val tokenStream = CommonTokenStream(lexer)
        val parser = MySqlParser(tokenStream)

        var errors = 0
        parser.addErrorListener(object : BaseErrorListener() {
            override fun syntaxError(recognizer: Recognizer<*, *>, offendingSymbol: Any?, line: Int, charPositionInLine: Int, msg: String, e: RecognitionException?) {
                println(msg + line + " " + charPositionInLine)
                errors++
            }
        })

        lexer.addErrorListener(object : BaseErrorListener() {
            override fun syntaxError(recognizer: Recognizer<*, *>, offendingSymbol: Any?, line: Int, charPositionInLine: Int, msg: String, e: RecognitionException?) {
                println(msg + line + " " + charPositionInLine)
                errors++
            }
        })

        val root = parser.root()
        Assert.assertEquals(0, errors)
        Assert.assertNotNull(root)
        Assert.assertNotNull(root.findSqlStatements())
        Assert.assertTrue(root.findSqlStatements()!!.findSqlStatement()!!.isEmpty())

    }

}
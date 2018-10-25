import org.antlr.v4.kotlinruntime.*
import org.junit.Assert
import org.junit.Test
import kotlin.experimental.and

/**
 * @author ozinoviev
 * @since 08.10.18
 */
class MySqlParserTest {
    @Test
    fun test_dml_select() {
        val stream = CharStreams.fromStream(this.javaClass.classLoader.getResourceAsStream("dml_select.sql"))
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
        Assert.assertEquals(59, root.findSqlStatements()!!.findSqlStatement()!!.size)
    }

    @Test
    fun test_bitrix_queires_cut() {
        val stream = CharStreams.fromStream(this.javaClass.classLoader.getResourceAsStream("bitrix_queires_cut.sql"))
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
        Assert.assertEquals(529, root.findSqlStatements()!!.findSqlStatement()!!.size)

    }

}
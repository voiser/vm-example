// Generated from /Users/david/vm/src/main/g4/Play.g4 by ANTLR 4.5.3
package grammar;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link PlayParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface PlayVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link PlayParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(PlayParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlayParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(PlayParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlayParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmt(PlayParser.StmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlayParser#defn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefn(PlayParser.DefnContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlayParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(PlayParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlayParser#ref}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRef(PlayParser.RefContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlayParser#call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCall(PlayParser.CallContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlayParser#args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgs(PlayParser.ArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlayParser#anon}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnon(PlayParser.AnonContext ctx);
	/**
	 * Visit a parse tree produced by {@link PlayParser#params}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParams(PlayParser.ParamsContext ctx);
}
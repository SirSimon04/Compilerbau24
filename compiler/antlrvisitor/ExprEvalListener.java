package compiler.antlrvisitor;

import compiler.antlrcompiler.languageParser;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

public class ExprEvalListener extends compiler.antlrcompiler.languageBaseListener {    
    public ParseTreeProperty<Integer> m_values = new ParseTreeProperty<Integer>();

	// questionMarkExpr: andOrExpr;

    // andOrExpr: cmpExpr;

    // cmpExpr: shiftExpr;
    public void exitShiftExpr(languageParser.ShiftExprContext ctx) {
        int cnt = ctx.getChildCount();
        int curChildIdx = 0;
        int curNumberIdx = 0;
        int curOpIdx = 0;
        int curResult = m_values.get(ctx.bitAndOrExpr(0));
        curChildIdx++;
        curNumberIdx++;
        while (curChildIdx < cnt) {
            TerminalNode nextOp = ctx.SHIFTOP(curOpIdx);
            curOpIdx++;
            curChildIdx++;
            int nextNumber = m_values.get(ctx.bitAndOrExpr(curNumberIdx));
            if (nextOp.getText().equals("<<")) {
                curResult = curResult << nextNumber;
            } else {
                curResult = curResult >> nextNumber;
            }
            curNumberIdx++;
            curChildIdx++;
        }
        m_values.put(ctx, curResult);
    }

    // shiftExpr: bitAndOrExpr;

    // bitAndOrExpr: sumExpr;

    // sumExpr: mulDivExpr (SUMOP  mulDivExpr)*;
    public void exitSumExpr(compiler.antlrcompiler.languageParser.SumExprContext ctx) {
        int cnt = ctx.getChildCount();
        int curChildIdx = 0;
        int curNumberIdx = 0;
        int curOpIdx = 0;
        int curResult = m_values.get(ctx.mulDivExpr(0));
        curChildIdx++;
        curNumberIdx++;
        while (curChildIdx < cnt) {
          TerminalNode nextOp = ctx.SUMOP(curOpIdx);
          curOpIdx++;
          curChildIdx++;
          int nextNumber = m_values.get(ctx.mulDivExpr(curNumberIdx));
          if (nextOp.getText().equals("+")) {
            curResult += nextNumber;
          } else {
            curResult -= nextNumber;
          }
          curNumberIdx++;
          curChildIdx++;
        }
        m_values.put(ctx, curResult);
    }

    // mulDivExpr: unaryExpr;

    // unaryExpr: dashExpr;

    // dashExpr: arrowExpr;

    // arrowExpr: parantheseExpr;

    // parantheseExpr: NUMBER; 
    public void exitNumber(compiler.antlrcompiler.languageParser.NumberContext ctx) {
        m_values.put(ctx, Integer.valueOf(ctx.NUMBER().getText()));
    }

}

package fr.beber.generatormdp.util;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextPaint;
import fr.beber.generatormdp.R;

/**
 * Cette classe permet de créer la première lettre une lettre en format bitmap.
 *
 * @author Bertrand
 * @version 1.0
 */
public class LetterTileProvider {

    /**
     * Nombre minimal de couleur dans le tableau.
     */
    private static final int NUM_OF_TILE_COLORS = 21;

    /**
     * Le {@link TextPaint} utilisait pour dessiner la lettre pour l'icone.
     */
    private final TextPaint mPaint = new TextPaint();

    /**
     * Définition du rectangle.
     */
    private final Rect mBounds = new Rect();

    /**
     * Le {@link Canvas} nécessaire pour le dessin du rectangle.
     */
    private final Canvas mCanvas = new Canvas();

    /**
     * La première lettre à utliser.
     */
    private final char[] mFirstChar = new char[1];

    /**
     * Fond du titre.
     */
    private final TypedArray mColors;

    /**
     * La couleur de font de la lettre.
     */
    private final int mTileLetterFontSize;

    /**
     * L'image à afficher.
     */
    private final Bitmap mDefaultBitmap;

    /**
     * Constructeur.
     *
     * @param context Le {@link Context} à utiliser.
     */
    public LetterTileProvider(Context context) {
        final Resources res = context.getResources();

        mPaint.setTypeface(Typeface.create("sans-serif-light", Typeface.NORMAL));
        mPaint.setColor(Color.WHITE);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setAntiAlias(true);

        mColors = res.obtainTypedArray(R.array.letter_tile_colors);
        mTileLetterFontSize = res.getDimensionPixelSize(R.dimen.tile_letter_font_size);

        mDefaultBitmap = BitmapFactory.decodeResource(res, R.drawable.ic_launcher);
    }

    /**
     * Permet de savoir si la lettre est présent dans l'alphabet ou un numérique.
     *
     * @param c Le caractère à vérifier.
     * @return True si <code>c</code> est une lettre de l'alphabet ou un numérique
     */
    private static boolean isAlphabetLetterOrDigit(char c) {
        return 'A' <= c && c <= 'Z' || 'a' <= c && c <= 'z' || '0' <= c && c <= '9';
    }

    /**
     * Permet de récupérer la première lettre du texte à cha
     *
     * @param context Le {@link Context} à utiliser.
     * @param text    Le string sur lequel ce base la première lettre.
     * @return La première lettre en {@link Bitmap}.
     */
    public Bitmap getLetterIcon(final Context context, final String text) {
        final Resources res = context.getResources();
        final int tileSize = res.getDimensionPixelSize(R.dimen.letter_tile_size);

        return this.getLetterIcon(text, tileSize, tileSize);
    }

    /**
     * Permet de récupérer la première lettre du texte à cha
     *
     * @param text    Le string sur lequel ce base la première lettre.
     * @param width    Largeur de l'icon.
     * @param height    Hauteur de l'icon.
     * @return La première lettre en {@link Bitmap}.
     */
    public Bitmap getLetterIcon(final String text,final int width,final int height) {
        return this.getLetterTile(text, text, width, height);
    }

    /**
     * Permet de générer la lettre en bitMap.
     *
     * @param displayName Le nom utilisé pour créer la lettre.
     * @param key         La clé utilisé pour générer la couleur de fond.
     * @param width       La largeur du titre désiré.
     * @param height      La hauteur du titre désiré.
     * @return Une {@link Bitmap} qui contient la lettre générée.
     */
    public Bitmap getLetterTile(String displayName, String key, int width, int height) {
        final Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        final char firstChar = displayName.charAt(0);

        final Canvas canvas = mCanvas;
        canvas.setBitmap(bitmap);
        canvas.drawColor(pickColor(key));

        if (isAlphabetLetterOrDigit(firstChar)) {
            mFirstChar[0] = Character.toUpperCase(firstChar);
            mPaint.setTextSize(mTileLetterFontSize);
            mPaint.getTextBounds(mFirstChar, 0, 1, mBounds);
            canvas.drawText(mFirstChar, 0, 1, width / 2, height / 2
                    + (mBounds.bottom - mBounds.top) / 2, mPaint);
        } else
            canvas.drawBitmap(mDefaultBitmap, 0, 0, null);

        return bitmap;
    }

    /**
     * Détermine la couleur de fond de l'icone.
     *
     * @param key La clé utilisé pour générer la couleur de fond.
     * @return La couleur choisi.
     */
    private int pickColor(final String key) {
        final int color = Math.abs(key.hashCode()) % NUM_OF_TILE_COLORS;
        try {
            return mColors.getColor(color, Color.BLACK);
        } finally {
            mColors.recycle();
        }
    }

}

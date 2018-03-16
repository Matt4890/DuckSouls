package animations;

import javafx.animation.Transition;
import javafx.util.Duration;
import javafx.animation.Interpolator;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.animation.Animation;

/**
 * The animation for running up to, or away from the enemy.
 * 
 * @author Wylee McAndrews
 */
public class SpriteAnimation extends Transition {
	
	/*
	 * 
	 * INSTANCE VARIABLES
	 * 
	 */
	
	private final ImageView	imageView;
	private final int		count;
	private final int		columns;
	private final int		width;
	private final int		height;
	private int				offsetX;
	private int				offsetY;
	
	/*
	 * 
	 * CONSTRUCTORS
	 * 
	 */
	
	/**
	 * Creates a sprite animation object.
	 * 
	 * @param imageView The sprite sheet object for the animation.
	 * @param duration Duration in milliseconds to run the animation.
	 * @param count Number of total images in the sprite sheet.
	 * @param columns Number of frames of animation.
	 * @param offsetX ImageView x offset.
	 * @param offsetY ImageView y offset.
	 * @param width Width of each frame.
	 * @param height Height of each frame.
	 */
	public SpriteAnimation(ImageView imageView, Duration duration, int count, int columns, int offsetX, int offsetY,
			int width, int height) {
		
		this.imageView = imageView;
		this.count = count;
		this.columns = columns;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.width = width;
		this.height = height;
		
		// Animation attributes
		setCycleDuration(duration);
		setCycleCount(Animation.INDEFINITE);
		setInterpolator(Interpolator.LINEAR);
		
		this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
		
	}
	
	/*
	 * 
	 * METHODS
	 * 
	 */
	
	/**
	 * Sets the x offset.
	 * 
	 * @param x The new x offset.
	 */
	public void setOffsetX(int x) {
		this.offsetX = x;
	}

	
	/**
	 * Sets the y offset.
	 * 
	 * @param y The new x offset.
	 */
	public void setOffsetY(int y) {
		this.offsetY = y;
	}
	
	/**
	 * Runs through the animation, displaying each image.
	 */
	protected void interpolate(double k) {
		
		// Find the image
		final int index = Math.min((int) Math.floor(count * k), count - 1);
		final int x = (index % columns) * width + offsetX;
		final int y = (index / columns) * height + offsetY;
		
		// Set the image
		imageView.setViewport(new Rectangle2D(x, y, width, height));
		
	}
	
}
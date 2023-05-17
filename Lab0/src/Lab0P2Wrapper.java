public class Lab0P2Wrapper {

	public static interface BaseVector {
		public double getXComponent();

		public double getYComponent();

		public double getMagnitude();

		public double getAngle();

		public BaseVector add(BaseVector V);

		public BaseVector subtract(BaseVector V);

		public double dotProduct(BaseVector V);

		public BaseVector crossProduct(BaseVector V);
	}

	public static class VectorMagnitude implements BaseVector {

		public double magnitude; // The magnitude of the vector
		public double angle; // The angle of the vector, in degrees

		public VectorMagnitude(double magnitude, double angle) {
			/* TODO ADD YOUR CODE HERE */
			this.magnitude = magnitude;
			this.angle = angle;
		}

		@Override
		public double getMagnitude() {
			/* TODO ADD YOUR CODE HERE */
			return magnitude;
		}

		public void setMagnitude(double magnitude) {
			/* TODO ADD YOUR CODE HERE */
			this.magnitude = magnitude;
		}

		@Override
		public double getAngle() {
			/* TODO ADD YOUR CODE HERE */
			return angle;
		}

		public void setAngle(double angle) {
			/* TODO ADD YOUR CODE HERE */
			this.angle = angle;
		}

		@Override
		public double getXComponent() {
			/* TODO ADD YOUR CODE HERE */
			return magnitude * Math.cos(Math.toRadians(angle));
		}

		@Override
		public double getYComponent() {
			/* TODO ADD YOUR CODE HERE */
			return magnitude * Math.sin(Math.toRadians(angle));
		}

		@Override
		public BaseVector add(BaseVector V) {
			/* TODO ADD YOUR CODE HERE */
			double newX = this.getXComponent() + V.getXComponent();
			double newY = this.getYComponent() + V.getYComponent();
			double newMagnitude = Math.sqrt(Math.pow(newX, 2) + Math.pow(newY, 2));
			double newAngle = Math.toDegrees(Math.atan2(newY, newX));
			return new VectorMagnitude(newMagnitude, newAngle);
		}

		@Override
		public BaseVector subtract(BaseVector V) {
			/* TODO ADD YOUR CODE HERE */
			double newX = this.getXComponent() - V.getXComponent();
			double newY = this.getYComponent() - V.getYComponent();
			double newMagnitude = Math.sqrt(Math.pow(newX, 2) + Math.pow(newY, 2));
			/*
			 * atan2() is a function in the Java Math library that returns the angle of a
			 * point in a Cartesian coordinate system, with the angle measured in radians
			 * from the x-axis. The atan2() function takes two arguments, the y-coordinate
			 * and the x-coordinate of the point, and returns the angle between the positive
			 * x-axis and the line connecting the point to the origin (0,0).
			 * 
			 * Unlike the atan() function, the atan2() function can return the angle in the
			 * full range of -π to π radians, so it can distinguish the correct quadrant of
			 * the angle. This means that the result of atan2() will be correct even if the
			 * point is in the second or third quadrant.
			 * 
			 * It's widely used in physics, trigonometry and engineering related
			 * calculations.
			 * 
			 */
			double newAngle = Math.toDegrees(Math.atan2(newY, newX));
			return new VectorMagnitude(newMagnitude, newAngle);
		}

		@Override
		public double dotProduct(BaseVector V) {
			/* TODO ADD YOUR CODE HERE */
			return (this.getXComponent() * V.getXComponent()) + (this.getYComponent() * V.getYComponent());
		}

		@Override
		public BaseVector crossProduct(BaseVector V) {
			/**
			 * Can you do a cross product of 2D vectors?
			 * If not, what should we do?
			 * 
			 * Hint: Look up what UnsopportedOperationException does!
			 */
			throw new UnsupportedOperationException("Cross product not supported for 2D vectors!");
		}
	}
}
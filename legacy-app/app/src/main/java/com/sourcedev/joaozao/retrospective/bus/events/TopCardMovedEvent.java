package com.sourcedev.joaozao.retrospective.bus.events;

/**
 * Created by joaozao on 06/02/17.
 */

public class TopCardMovedEvent {

  // region Fields
  private final float posX;
  // endregion

  // region Constructors
  public TopCardMovedEvent(float posX) {
    this.posX = posX;
  }
  // endregion

  // region Getters
  public float getPosX() {
    return posX;
  }
  // endregion
}

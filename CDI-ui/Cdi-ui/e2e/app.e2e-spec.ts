import { CdiUiPage } from './app.po';

describe('cdi-ui App', () => {
  let page: CdiUiPage;

  beforeEach(() => {
    page = new CdiUiPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
